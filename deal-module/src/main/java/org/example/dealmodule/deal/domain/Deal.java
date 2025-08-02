package org.example.dealmodule.deal.domain;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Deal(
        UUID id,
        String title,
        Integer stage,
        String expectedRevenue,
        LocalDateTime expectedCloseDate,
        UUID contact,
        UUID owner
) {
}
