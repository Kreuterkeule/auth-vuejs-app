package com.kreuterkeule.authvuejsapp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Token {
    @Getter
    private final String token;

    public static Token of(Long userId, Long validityInMinutes, String secretKey) {

        var now = Instant.now();

        return new Token(
                Jwts.builder()
                    .claim("user_id", userId)
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plus(validityInMinutes, ChronoUnit.MINUTES)))
                    .signWith(SignatureAlgorithm.HS512, Base64.encodeBase64String(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .compact()
        );

    }

    private Token(String token) {
        this.token = token;
    }
}
