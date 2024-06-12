package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.response.CertificateResponseDTO;

public interface ICertificateService {
    CertificateResponseDTO getCertificateById(Long id);
    CertificateResponseDTO createCertificate(CertificateResponseDTO certificateResponseDTO);
}
