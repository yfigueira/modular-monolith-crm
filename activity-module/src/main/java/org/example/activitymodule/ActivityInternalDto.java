package org.example.activitymodule;

import lombok.Builder;
import lombok.With;
import org.example.activitymodule.activity.domain.Activity;
import org.example.activitymodule.activity.domain.ActivityType;
import org.example.activitymodule.common.web.DtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Builder
public record ActivityInternalDto(
        UUID id,
        String subject,
        String description,
        ActivityType type,
        @With LocalDateTime scheduledAt
) {
    @Mapper
    public interface ActivityInternalDtoMapper extends DtoMapper<Activity, ActivityInternalDto> {

        default ActivityType mapActivityType(Integer dto) {
            return Arrays.stream(ActivityType.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(ActivityType.NOT_AVAILABLE);
        }

        default Integer mapActivityType(ActivityType domain) {
            return domain.getCode();
        }
    }

    public static ActivityInternalDtoMapper mapper() {
        return Mappers.getMapper(ActivityInternalDtoMapper.class);
    }
}
