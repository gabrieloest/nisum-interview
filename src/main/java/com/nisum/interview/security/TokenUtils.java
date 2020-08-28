package com.nisum.interview.security;

import com.auth0.jwt.JWT;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.nisum.interview.security.SecurityConstants.EXPIRATION_TIME;
import static com.nisum.interview.security.SecurityConstants.SECRET;

@Component
public class TokenUtils {

    public static String generateToken(String subject) {
        String token = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        return token;
    }
}
