package com.lslm.accountsapi.adapters.requests;

public record AuthenticationRequest(
        String email,
        String password
) {
}
