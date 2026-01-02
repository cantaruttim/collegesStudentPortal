package br.com.adaicollege.studentPortal.config.utils;

import br.com.adaicollege.studentPortal.config.security.auth.MyToken;
import br.com.adaicollege.studentPortal.model.login.UserLogin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

public class TokenUtil {

    public static final String EMISSOR = "ADAI_COLLEGE";
    public static final long EXPIRATION = 60 * 60 * 1000;
    public static final String SECRET_KEY = "0123456789012345678901234567890123456789";


    public static MyToken encode(UserLogin user) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

            String jwtToken = Jwts.builder()
                    .subject(user.getId())
                    .issuer(EMISSOR)
                    .claim("roles", user.getRoles())
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .signWith(key)
                    .compact();

            return new MyToken(jwtToken);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Authentication decode(HttpServletRequest request) {

        try {
            String header = request.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) {
                return null;
            }

            String token = header.replace("Bearer", "").trim();

            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            JwtParser parser = Jwts.parser().verifyWith(key).build();
            Claims claims = (Claims) parser.parse(token).getPayload();

            String subjectId = claims.getSubject();
            String issuer = claims.getIssuer();
            Date expiration = claims.getExpiration();

            if (!EMISSOR.equals(issuer)
                    || subjectId == null
                    || expiration == null
                    || expiration.before(new Date())) {
                return null;
            }

            List<String> roles = claims.get("roles", List.class);
            if (roles == null) {
                roles = List.of();
            }

            var authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .toList();

            return new UsernamePasswordAuthenticationToken(
                    subjectId,
                    null,
                    authorities
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

