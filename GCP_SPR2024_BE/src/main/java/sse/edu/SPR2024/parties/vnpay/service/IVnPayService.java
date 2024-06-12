package sse.edu.SPR2024.parties.vnpay.service;

import jakarta.servlet.http.HttpServletRequest;

public interface IVnPayService {
    String createOrder(int total, String orderInfor, String urlReturn);
}
