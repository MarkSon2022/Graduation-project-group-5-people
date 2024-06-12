package sse.edu.SPR2024.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sse.edu.SPR2024.dto.request.SubscriptionRequestDTO;
import sse.edu.SPR2024.dto.response.SubscriptionResponseDTO;
import sse.edu.SPR2024.entity.*;
import sse.edu.SPR2024.enums.CourseStatus;
import sse.edu.SPR2024.enums.SubscriptionStatus;
import sse.edu.SPR2024.enums.UserRole;
import sse.edu.SPR2024.exception.ServiceDataException;
import sse.edu.SPR2024.parties.vnpay.config.VnPayConfig;
import sse.edu.SPR2024.parties.vnpay.service.IVnPayService;
import sse.edu.SPR2024.parties.vnpay.service.VnPayService;
import sse.edu.SPR2024.repository.*;
import sse.edu.SPR2024.service.ISubscriptionService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SubscriptionService implements ISubscriptionService {
    private final ISubscriptionRepository subscriptionRepository;
    private final IUserRepository userRepository;
    private final IMentorRepository mentorRepository;
    private final ICoursePackageRepository coursePackageRepository;
    private final IOrganizationRepository organizationRepository;
    private final IVnPayService vnPayService;
    private final ModelMapper modelMapper;

    /**
     * @param subscriptionRequestDTO
     * @return
     */
    @Override
    public SubscriptionResponseDTO create(SubscriptionRequestDTO subscriptionRequestDTO, HttpServletRequest request) {
        Subscription subscription = null;
        String paymentUrl = "";

        try {
            CoursePackage coursePackage = coursePackageRepository
                    .findById(subscriptionRequestDTO.getCoursePackageId())
                    .orElseThrow(() -> new ServiceDataException("Can not found course package"));
            Organization organization = organizationRepository
                    .findById(subscriptionRequestDTO.getOrganizationId())
                    .orElseThrow(() -> new ServiceDataException("Can not found organization"));

            System.out.println(subscriptionRequestDTO);

            Subscription subscriptionSave = modelMapper.map(subscriptionRequestDTO, Subscription.class);
            subscriptionSave.setOrg(organization);
            subscriptionSave.setCoursePackage(coursePackage);
            subscriptionSave.setStatus(SubscriptionStatus.PENDING.name());
            subscriptionSave.setCreatedDate(OffsetDateTime.now());
            subscriptionSave.setStartDate(OffsetDateTime.now());
            subscriptionSave.setEndDate(OffsetDateTime.now().plusMonths(6));
            subscription = subscriptionRepository.save(subscriptionSave);

            String baseUrl = request.getScheme() + "://" + request.getServerName() + ":3000/confirm-subscription" ;

            paymentUrl = vnPayService.createOrder(coursePackage.getPrice().intValueExact(), subscription.getId(), baseUrl);
        }
        catch (Exception exception) {
            subscription.setStatus(SubscriptionStatus.SYSTEM_FAILED.name());
        }

        SubscriptionResponseDTO subscriptionResponseDTO = modelMapper.map(subscriptionRepository.save(subscription), SubscriptionResponseDTO.class);
        subscriptionResponseDTO.setPaymentUrl(StringUtils.isEmpty(paymentUrl) ? null : paymentUrl);

        return subscriptionResponseDTO;
    }

    /**
     * @param subscriptionRequestDTO
     * @return
     */
    @Override
    public SubscriptionResponseDTO update(SubscriptionRequestDTO subscriptionRequestDTO) {
        Subscription existingSubscription = subscriptionRepository.findById(subscriptionRequestDTO.getId()).orElseThrow(() -> new ServiceDataException(MessageFormat.format("Subscription not found with id: {0}", subscriptionRequestDTO.getId())));
        existingSubscription.setStartDate(subscriptionRequestDTO.getStartDate());
        existingSubscription.setEndDate(subscriptionRequestDTO.getEndDate());
        existingSubscription.setBoughtPrice(subscriptionRequestDTO.getBoughtPrice());
        existingSubscription.setBoughtMaxStudent(subscriptionRequestDTO.getBoughtMaxStudent());
        existingSubscription.setModifiedDate(OffsetDateTime.now());
        return modelMapper.map(subscriptionRepository.save(existingSubscription), SubscriptionResponseDTO.class);
    }

    @Override
    public List<SubscriptionResponseDTO> getSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();

        return subscriptions.stream().map(ele -> {
            return modelMapper.map(ele, SubscriptionResponseDTO.class);
        }).collect(Collectors.toList());
    }

    @Override
    public SubscriptionResponseDTO updateStatus(HttpServletRequest request) {
        Subscription existingSubscription = subscriptionRepository.findById(request.getParameter("vnp_OrderInfo")).orElseThrow(() -> new ServiceDataException(MessageFormat.format("Subscription not found with id: {0}", request.getParameter("vnp_OrderInfo"))));
        if (Objects.nonNull(existingSubscription)) {
            boolean payStatus = orderReturn(request) == 1;
            if (payStatus) {
                existingSubscription.setStatus(SubscriptionStatus.PAID.name());
            }
            else {
                existingSubscription.setStatus(SubscriptionStatus.PAY_FAILED.name());
            }
        }
        return modelMapper.map(subscriptionRepository.save(existingSubscription), SubscriptionResponseDTO.class);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SubscriptionResponseDTO getSubcriptionById(String id) {
        Optional<Subscription> subscriptionOptional = subscriptionRepository.findById(id);

        if (subscriptionOptional.isEmpty()) {
            throw new ServiceDataException("Can not find subscription");
        }

        return modelMapper.map(subscriptionOptional.get(), SubscriptionResponseDTO.class);
    }

    @Override
    public List<SubscriptionResponseDTO> getActiveSubcriptionByMentorId(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceDataException("Can not find mentor"));

        if (user.getRole().equals(UserRole.ORGANIZATION.name())) {
            List<Subscription> subscriptions = subscriptionRepository
                    .findAllByOrgOrgId(userId)
                    .stream()
                    .filter(ele -> {
                        return ele.getStatus().equals(SubscriptionStatus.PAID.name());
                    }).collect(Collectors.toList());

            return subscriptions.stream()
                    .map(ele -> {
                    return modelMapper.map(ele, SubscriptionResponseDTO.class);
            }).collect(Collectors.toList());
        }

        Mentor mentor = mentorRepository.findById(userId)
                .orElseThrow(() -> new ServiceDataException("Can not find mentor"));

        String orgId = mentor.getOrganization().getOrgId();

        List<Subscription> subscriptions = subscriptionRepository
                .findAllByOrgOrgId(orgId)
                .stream()
                .filter(ele -> {
                    return ele.getStatus().equals(SubscriptionStatus.PAID.name());
                }).collect(Collectors.toList());

        return subscriptions.stream()
                .filter(x -> x.getCoursePackage().getCourse().getStatus().equals(CourseStatus.ACTIVE.name())).map(ele -> {
            return modelMapper.map(ele, SubscriptionResponseDTO.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<SubscriptionResponseDTO> getAllSubcriptionByOrgId(String orgId) {
        List<Subscription> subscriptions = subscriptionRepository
                .findAllByOrgOrgId(orgId);

        return subscriptions.stream()
                .map(ele -> {
                    return modelMapper.map(ele, SubscriptionResponseDTO.class);
                }).collect(Collectors.toList());

    }

    private int orderReturn(HttpServletRequest request){
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = VnPayConfig.hashAllFields(fields);
        if (signValue.contains(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
//                SubscriptionRequestDTO subscriptionRequestDTO = SubscriptionRequestDTO
//                        .builder()
//                        .id(request.getParameter("vnp_OrderInfo"))
//                        .status(SubscriptionStatus.PAID.name())
//                        .build();
//                subscriptionService.updateStatus(subscriptionRequestDTO);
                return 1;
            } else {
//                SubscriptionRequestDTO subscriptionRequestDTO = SubscriptionRequestDTO
//                        .builder()
//                        .id(request.getParameter("vnp_OrderInfo"))
//                        .status(SubscriptionStatus.PAY_FAILED.name())
//                        .build();
//                subscriptionService.updateStatus(subscriptionRequestDTO);
                return 0;
            }
        } else {
            return -1;
        }
    }
}
