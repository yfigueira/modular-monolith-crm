package org.example.activitymodule.activity.web.dto;

import lombok.Builder;
import org.example.activitymodule.Activity;
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
public record ActivitySummary(
        UUID id,
        String subject,
        LocalDateTime scheduledAt,
        Integer type,
        Integer status,
        Integer entityType
) {
    @Mapper
    public interface ActivitySummaryMapper extends DtoMapper<Activity, ActivitySummary> {

        default ActivityType mapActivityType(Integer dto) {
            return Arrays.stream(ActivityType.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(ActivityType.NOT_AVAILABLE);
        }

        default Integer mapActivityType(ActivityType domain) {
            return domain.getCode();
        }

        default ActivityStatus mapActivityStatus(Integer dto) {
            return Arrays.stream(ActivityStatus.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(ActivityStatus.NOT_AVAILABLE);
        }

        default Integer mapActivityStatus(ActivityStatus domain) {
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

    public static ActivitySummaryMapper mapper() {
        return Mappers.getMapper(ActivitySummaryMapper.class);
    }
}
