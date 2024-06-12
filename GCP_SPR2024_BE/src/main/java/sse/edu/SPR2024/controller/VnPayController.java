package sse.edu.SPR2024.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sse.edu.SPR2024.parties.vnpay.service.VnPayService;

@RestController
@RequestMapping("/api/v1/vnpay")
@RequiredArgsConstructor
public class VnPayController {
    private final VnPayService vnPayService;

    @PostMapping
    public String submitOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request){
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        return vnpayUrl;
    }

//    @GetMapping
//    public String GetMapping(HttpServletRequest request, Model model){
//        int paymentStatus =vnPayService.orderReturn(request);
//
//        String orderInfo = request.getParameter("vnp_OrderInfo");
//        String paymentTime = request.getParameter("vnp_PayDate");
//        String transactionId = request.getParameter("vnp_TransactionNo");
//        String totalPrice = request.getParameter("vnp_Amount");
//
//        model.addAttribute("orderId", orderInfo);
//        model.addAttribute("totalPrice", totalPrice);
//        model.addAttribute("paymentTime", paymentTime);
//        model.addAttribute("transactionId", transactionId);
//
//        return paymentStatus == 1 ? "Pay for subscription success" : "Pay for subscription fail";
//    }
}
