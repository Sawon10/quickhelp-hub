package com.oauth.quickhelp.service;

import com.oauth.quickhelp.entity.UserDetails;
import com.oauth.quickhelp.entity.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JWTService {

    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);
    private final SecretKey signingKey;
    private final long expirationMillis;

    public JWTService(
            @Value("${jwt.secret:}") String secret,
            @Value("${jwt.expiration-ms:3600000}") long expirationMillis
    ) {
        if (secret == null || secret.isBlank()) {
            logger.error("Jwt secret cannot be null, please check the configuration");
            throw new IllegalStateException("jwt.secret must be configured before generating JWTs.");
        }
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMillis = expirationMillis;
    }

    public String generateToken(UserDetails userDetails) {
        logger.info("Inside JWTService.generateToken method");
        Instant now = Instant.now();
        List<String> roles = userDetails.getRoles().stream()
                .map(UserRole::getRoleName)
                .toList();

        Map<String, Object> claims = Map.of(
                "userId", userDetails.getUserId(),
                "email", userDetails.getEmail(),
                "roles", roles
        );

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setClaims(claims)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(expirationMillis)))
                .signWith(signingKey)
                .compact();
    }
}
