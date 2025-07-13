package org.example.usermodule.user.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record User(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
