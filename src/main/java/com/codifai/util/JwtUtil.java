package com.codifai.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "maCleSecrete123"; // à externaliser en config

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10h
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Ajoute méthodes pour valider et extraire username si besoin
}
