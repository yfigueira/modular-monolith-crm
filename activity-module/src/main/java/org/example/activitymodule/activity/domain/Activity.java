package org.example.activitymodule.activity.domain;

import lombok.Builder;
import lombok.With;
import org.example.activitymodule.user.domain.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Activity(
        UUID id,
        String subject,
        String description,
        @With LocalDateTime scheduledAt,
        LocalDateTime completedAt,
        ActivityType type,
        ActivityStatus status,
        UUID entity,
        EntityType entityType,
        @With User owner,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
