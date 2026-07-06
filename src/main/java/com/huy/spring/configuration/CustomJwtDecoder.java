package com.huy.spring.configuration;

import com.huy.spring.domain.dto.request.IntrospectRequest;
import com.huy.spring.exception.AppExeption;
import com.huy.spring.exception.ErrorCode;
import com.huy.spring.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomJwtDecoder implements JwtDecoder {
    final AuthenticationService authenticationService;
    NimbusJwtDecoder nimbusJwtDecoder = null;
    @Value("${jwt.signerKey}")
    String signerKey;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            var response = this.authenticationService.introspectResponse(IntrospectRequest.builder()
                    .token(token)
                    .build());
            if (!response.isValid()) {
                throw new AppExeption(ErrorCode.UNAUTHENTICATED);
            }
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS256");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS256)
                    .build();
        }
        return nimbusJwtDecoder.decode(token);
    }
}
