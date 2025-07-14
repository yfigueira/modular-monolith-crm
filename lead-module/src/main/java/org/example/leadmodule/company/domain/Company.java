package org.example.leadmodule.company.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Company(
        UUID id,
        String name
) {
}
