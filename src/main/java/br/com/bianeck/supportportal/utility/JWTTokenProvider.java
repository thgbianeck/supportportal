package br.com.bianeck.supportportal.utility;

import br.com.bianeck.supportportal.domain.UserPrincipal;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

import static br.com.bianeck.supportportal.constant.SecurityConstant.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JWTTokenProvider {

    @Value("jwt.secret")
    private String secret;
    
    private String generateJwtToken(UserPrincipal userPrincipal) {
        String[] claims = getClaimsFromUser(userPrincipal);
        return JWT.create()
                .withIssuer(GET_ARRAYS_LLC)
                .withAudience(GET_ARRAYS_ADMINISTRATION)
                .withIssuedAt(new Date())
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(secret.getBytes()));


    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
        return new String[0];
    }

}
