package org.example.leadmodule.jobtitle.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JobTitle(
        UUID id,
        String name
) {
}
