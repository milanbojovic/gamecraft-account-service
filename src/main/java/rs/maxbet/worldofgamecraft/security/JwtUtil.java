package rs.maxbet.worldofgamecraft.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "accounts-service-secret-key";
    private static final long EXPIRATION_TIME = 21_600_000; // 6 hours

    public String generateToken(String username, String role, int id) {
        return Jwts.builder()
                .claim("id", id)
                .claim("role", role)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}