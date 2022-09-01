package com.lslm.accountsapi.adapters.requests;

public record AccountRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
