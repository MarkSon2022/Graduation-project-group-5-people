package sse.edu.SPR2024.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
@RestController
@RequestMapping("/")
public class HomeRestController {
    @GetMapping("/get_ip")
    public String HomeRestController(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            System.out.println(InetAddress.getLocalHost().getAddress());
            System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
            System.out.println(InetAddress.getLocalHost().getHostName());
            ipAddress += " - " + hostname;
        }catch (UnknownHostException ex){
            ipAddress += " - " + ex.getMessage();
        }
        System.out.println("Haha");
        return ipAddress;
    }


}
