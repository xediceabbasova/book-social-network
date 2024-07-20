package com.company.book.auth;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token
) {
}
