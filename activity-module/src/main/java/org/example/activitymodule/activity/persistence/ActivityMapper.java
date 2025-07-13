package org.example.activitymodule.activity.persistence;

import org.example.activitymodule.activity.domain.Activity;
import org.example.activitymodule.activity.domain.ActivityStatus;
import org.example.activitymodule.activity.domain.ActivityType;
import org.example.activitymodule.activity.domain.EntityType;
import org.example.activitymodule.user.domain.User;
import org.mapstruct.*;

import java.util.Arrays;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ActivityMapper {

    Activity toDomain(ActivityEntity entity);

    ActivityEntity toEntity(Activity domain);

    default ActivityStatus mapActivityStatus(Integer entity) {
        return Arrays.stream(ActivityStatus.values())
                .filter(v -> v.getCode().equals(entity))
                .findFirst()
                .orElse(ActivityStatus.NOT_AVAILABLE);
    }

    default Integer mapActivityStatus(ActivityStatus domain) {
        return domain.getCode();
    }

    default ActivityType mapActivityType(Integer entity) {
        return Arrays.stream(ActivityType.values())
                .filter(v -> v.getCode().equals(entity))
                .findFirst()
                .orElse(ActivityType.NOT_AVAILABLE);
    }

    default Integer mapActivityType(ActivityType domain) {
        return domain.getCode();
    }

    default EntityType mapEntityType(Integer entity) {
        return Arrays.stream(EntityType.values())
                .filter(v -> v.getCode().equals(entity))
                .findFirst()
                .orElse(EntityType.NOT_AVAILABLE);
    }

    default Integer mapEntityType(EntityType domain) {
        return domain.getCode();
    }

    default User mapOwner(UUID entity) {
        return User.builder().id(entity).build();
    }

    default UUID mapOwner(User domain) {
        return domain.id();
    }
}
