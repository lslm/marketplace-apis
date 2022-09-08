package com.lslm.accountsapi.adapters;

import com.lslm.accountsapi.adapters.responses.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationAdapter {
    public AuthenticationResponse toAuthenticationResponse(String token) {
        return AuthenticationResponse.builder()
                .type("Bearer")
                .token(token)
                .build();
    }
}
