package org.example.usermodule.user.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Token(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("expires_in")
        int expiresIn
) {
}
