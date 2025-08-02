package org.example.activitymodule.activity.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.example.activitymodule.activity.domain.Activity;
import org.example.activitymodule.activity.domain.ActivityStatus;
import org.example.activitymodule.activity.domain.ActivityType;
import org.example.activitymodule.activity.domain.EntityType;
import org.example.activitymodule.common.web.DtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Builder
public record ActivityDto(
        UUID id,
        @NotBlank(message = "Subject required")
        String subject,
        String description,
        LocalDateTime scheduledAt,
        LocalDateTime completedAt,
        @NotNull(message = "Activity type required")
        Integer type,
        Integer status,
        @NotNull(message = "Entity required")
        UUID entity,
        @NotNull(message = "Entity type required")
        Integer entityType,
        @NotNull(message = "Owner required")
        UUID owner
) {
    @Mapper
    public interface ActivityDtoMapper extends DtoMapper<Activity, ActivityDto> {

        default ActivityStatus mapActivityStatus(Integer dto) {
            return Arrays.stream(ActivityStatus.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(ActivityStatus.NOT_AVAILABLE);
        }

        default Integer mapActivityStatus(ActivityStatus domain) {
            return domain.getCode();
        }

        default ActivityType mapActivityType(Integer dto) {
            return Arrays.stream(ActivityType.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(ActivityType.NOT_AVAILABLE);
        }

        default Integer mapActivityType(ActivityType domain) {
            return domain.getCode();
        }

        default EntityType mapEntityType(Integer dto) {
            return Arrays.stream(EntityType.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(EntityType.NOT_AVAILABLE);
        }

        default Integer mapEntityType(EntityType domain) {
            return domain.getCode();
        }
    }

    public static ActivityDtoMapper mapper() {
        return Mappers.getMapper(ActivityDtoMapper.class);
    }
}
