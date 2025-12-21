package br.com.adaicollege.studentPortal.config.utils;

import br.com.adaicollege.studentPortal.config.security.MyToken;
import br.com.adaicollege.studentPortal.model.login.UserLogin;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

public class TokenUtil {

    public MyToken encode(UserLogin user) {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Authentication decode (HttpServletRequest request) {

        try {

            String header = request.getHeader("Authorization");

            if (header != null) {
                String token = header.replace("Bearer", "");

                if (token.equals("security123")) {
                    return new UsernamePasswordAuthenticationToken("Valid", null, Collections.emptyList());
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }


}
