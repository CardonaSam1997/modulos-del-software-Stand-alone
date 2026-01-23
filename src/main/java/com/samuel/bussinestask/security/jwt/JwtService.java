package com.samuel.bussinestask.security.jwt;
import com.samuel.bussinestask.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

        private static final String SECRET_KEY = "CLAVE_SUPER_SECRETA_DE_MIN_256_BITS";

        public String generateToken(User user) {
            return Jwts.builder()
                    .setSubject(user.getEmail())
                    .claim("role", user.getRole().name())
                    .setIssuedAt(new Date())
                    .setExpiration(
                            new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)
                    )
                    .signWith(getSignKey(), SignatureAlgorithm.HS256)
                    .compact();
        }

        public String extractUsername(String token) {
            return extractAllClaims(token).getSubject();
        }

        public boolean isTokenValid(String token, UserDetails userDetails) {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername())
                    && !isTokenExpired(token);
        }

        private boolean isTokenExpired(String token) {
            return extractAllClaims(token)
                    .getExpiration()
                    .before(new Date());
        }

        private Claims extractAllClaims(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

        private Key getSignKey() {
            return Keys.hmacShaKeyFor(
                    SECRET_KEY.getBytes(StandardCharsets.UTF_8)
            );
        }
}