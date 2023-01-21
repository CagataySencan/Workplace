package com.tdonuk.sepetim.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tdonuk.constant.HttpHeaders;
import com.tdonuk.constant.Time;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JWTUtils {
    private static DecodedJWT decodedJWT;
    public static final String EXAMPLE_KEY = "4226452948404D6351665468576D5A7134743777217A25432A462D4A614E6452";

    public static String getUser(String tokenHeader) throws Exception {
        if(Objects.isNull(decodedJWT)) {
            validate(tokenHeader);
        }
        return decodedJWT.getSubject();
    }

    public static List<SimpleGrantedAuthority> getAuthorities(String tokenHeader) throws Exception {
        if(Objects.isNull(decodedJWT)) {
            validate(tokenHeader);
        }
        return decodedJWT.getClaim("authorities").asList(SimpleGrantedAuthority.class);
    }

    public static void validate(String tokenHeader) throws Exception{
        try {
            decode(tokenHeader);
        } catch(TokenExpiredException e) {
            log.info("An error has occurred while authenticating the user: " + e.getMessage());
            throw new Exception("Maximum oturum süreniz dolmuştur. Lütfen yeniden giriş yapınız.");
        } catch(JWTDecodeException e) {
            throw new JWTDecodeException("Bilinmeyen bir hata oluştu: " + e. getMessage());
        } catch(Exception e) {
            throw e;
        }

    }

    private static DecodedJWT decode(String tokenHeader) {
        String token = tokenHeader.substring((HttpHeaders.BEARER + " ").length());
        Algorithm algorithm = Algorithm.HMAC256(EXAMPLE_KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();

        decodedJWT = verifier.verify(token);

        return decodedJWT;
    }

    public static String create(String subject, long expiresAt, Algorithm alg, List<String> authorities) {
        String token = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiresAt))
                .withClaim("authorities", authorities)
                .sign(alg);

        return token;
    }

    public static String createDefault(String subject, List<String> authorities) {
        return create(subject, Time.days(5), Algorithm.HMAC256(EXAMPLE_KEY), authorities);
    }
}