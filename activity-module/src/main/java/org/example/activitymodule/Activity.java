package org.example.activitymodule;

import lombok.Builder;
import lombok.With;
import org.example.activitymodule.activity.domain.ActivityStatus;
import org.example.activitymodule.activity.domain.ActivityType;
import org.example.activitymodule.activity.domain.EntityType;
import org.example.usermodule.User;

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
