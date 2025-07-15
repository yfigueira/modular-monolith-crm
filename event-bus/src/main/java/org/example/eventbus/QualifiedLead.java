package org.example.eventbus;

import lombok.Builder;

import java.util.UUID;

@Builder
public record QualifiedLead(
        UUID id,
        String firstName,
        String lastName,
        String email
) {
}
