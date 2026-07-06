package com.huy.spring.controller;

import com.huy.spring.domain.dto.request.IntrospectRequest;
import com.huy.spring.domain.dto.request.RefreshmentRequest;
import com.huy.spring.domain.dto.request.UserLoginRequest;
import com.huy.spring.domain.dto.request.UserLogoutRequest;
import com.huy.spring.domain.dto.response.ApiResponse;
import com.huy.spring.domain.dto.response.AuthenticationResponse;
import com.huy.spring.domain.dto.response.IntrospectResponse;
import com.huy.spring.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> loginRequest(@RequestBody UserLoginRequest loginRequest) {
        var rs = this.authenticationService.loginRequest(loginRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .response(rs)
                .message("login successfully!")
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logoutRequest(@RequestBody UserLogoutRequest request) throws ParseException, JOSEException {
        this.authenticationService.logoutRequest(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Logout successfully")
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> verifyToken(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var rs = this.authenticationService.introspectResponse(request);
        return ApiResponse.<IntrospectResponse>builder()
                .response(rs)
                .build();
    }

    @PostMapping("/refresh-token")
    public ApiResponse<AuthenticationResponse> refreshTokenRequest(@RequestBody RefreshmentRequest request) throws ParseException, JOSEException {
        var rs = this.authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .response(rs)
                .message("Refresh token successfully!")
                .build();
    }
}
