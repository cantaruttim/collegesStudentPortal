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

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collections;
import java.util.Date;


public class TokenUtil {

    public static final String EMISSOR = "ADAI_COLLEGE";
    public static final long EXPIRATION = 60 * 60 * 1000;
    public static final String SECRET_KEY = "0123456789012345678901234567890123456789";

    public static MyToken encode(UserLogin user) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); // private key
            String jwtToken = Jwts.builder()
                    .subject(user.getRegistrationNumber())
                    .expiration(new Date(System.currentTimeMillis()+EXPIRATION))
                    .issuer(EMISSOR)
                    .signWith(key)
                    .compact();

            return new MyToken(jwtToken);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Authentication decode (HttpServletRequest request) {

        try {

            String header = request.getHeader("Authorization");

            if (header != null) {
                String token = header.replace("Bearer", "").trim();

                SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
                JwtParser parser = Jwts.parser().verifyWith(key).build();
                Claims claims = (Claims)parser.parse(token).getPayload();

                String subject = claims.getSubject();
                String issuer = claims.getIssuer();
                Date exp = claims.getExpiration();

                if (issuer.equals(EMISSOR) && subject.length() > 0 && exp.after(new Date(System.currentTimeMillis()))) {
                    return new UsernamePasswordAuthenticationToken("Valid", null, Collections.emptyList());
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
