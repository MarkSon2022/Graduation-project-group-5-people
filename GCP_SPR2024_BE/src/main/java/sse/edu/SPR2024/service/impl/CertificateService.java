package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.response.CertificateResponseDTO;
import sse.edu.SPR2024.entity.Certificate;
import sse.edu.SPR2024.exception.ServiceDataException;
import sse.edu.SPR2024.repository.ICertificateRepository;
import sse.edu.SPR2024.service.ICertificateService;

@RequiredArgsConstructor
@Service
public class CertificateService implements ICertificateService {
    private final ICertificateRepository certificateRepository;
    private final ModelMapper modelMapper;

    @Override
    public CertificateResponseDTO getCertificateById(Long id) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new ServiceDataException("Certificate not found"));

        return modelMapper.map(certificate, CertificateResponseDTO.class);
    }

    @Override
    public CertificateResponseDTO createCertificate(CertificateResponseDTO certificateResponseDTO) {
        return null;
    }
}
