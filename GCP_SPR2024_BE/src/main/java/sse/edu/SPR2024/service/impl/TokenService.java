package sse.edu.SPR2024.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.response.UserResponseDTO;

import java.util.Date;

@Service
public class TokenService {

    private static final String SECRET_KEY = "ynVkhQPOxE5qhpM1F9hwFTbJTnUMxC0aJA2HIGafJ1Qw";

    public String generateToken(UserResponseDTO model) throws JOSEException {
        JWSSigner signer = new MACSigner(SECRET_KEY);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(model.getUserId())
                .issuer("yourIssuer")
                .expirationTime(new Date(new Date().getTime() + 3600000)) // Token valid for 1 hour
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

//    public UserResponseDTO parseToken(String token) {
//        Jws<Claims> claims = Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token);
//
//        Long modelId = Long.parseLong(claims.getBody().getSubject());
//
//        Model model = new Model();
//        model.setId(modelId);
//        return model;
//    }
}
