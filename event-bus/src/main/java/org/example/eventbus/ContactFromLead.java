package org.example.eventbus;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ContactFromLead(
        UUID leadId,
        UUID contactId
) {
}
