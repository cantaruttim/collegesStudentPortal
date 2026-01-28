package br.com.adaicollege.studentPortal.config.utils;

import br.com.adaicollege.studentPortal.config.security.auth.MyToken;
import br.com.adaicollege.studentPortal.model.login.UserLogin;
import io.jsonwebtoken.Claims;
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
import java.util.stream.Stream;

public class TokenUtil {

    public static final String EMISSOR = "ADAI_COLLEGE";
    public static final long EXPIRATION = 60 * 60 * 1000;
    public static final String SECRET_KEY = "0123456789012345678901234567890123456789";


    public static MyToken encode(UserLogin user) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

            String jwtToken = Jwts.builder()
                    .subject(user.getRegistrationNumber())
                    .issuer(EMISSOR)

                    .claim("roles", user.getRoles())
                    .claim("permissions", user.getPermissions())

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

            String token = header.replace("Bearer ", "");

            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .requireIssuer(EMISSOR)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String subject = claims.getSubject();

            List<String> roles = claims.get("roles", List.class);
            List<String> permissions = claims.get("permissions", List.class);

            if (roles == null) roles = List.of();
            if (permissions == null) permissions = List.of();

            var authorities = Stream.concat(
                    roles.stream()
                            .map(r -> new SimpleGrantedAuthority("ROLE_" + r)),
                    permissions.stream()
                            .map(SimpleGrantedAuthority::new)
            ).toList();

            System.out.println();
            System.out.println("------------------------");
            authorities.forEach(a -> System.out.println(a.getAuthority()));
            System.out.println("------------------------");
            System.out.println();

            return new UsernamePasswordAuthenticationToken(
                    subject,
                    null,
                    authorities
            );

        } catch (Exception e) {
            return null;
        }
    }
}

