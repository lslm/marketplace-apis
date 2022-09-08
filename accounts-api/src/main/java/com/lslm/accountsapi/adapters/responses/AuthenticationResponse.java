package com.lslm.accountsapi.adapters.responses;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String type,
        String token
) {
}
