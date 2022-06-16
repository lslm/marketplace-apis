package com.lslm.accountsapi.adapters.responses;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record AccountRegistrationResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        Instant createdAt
) {
}
