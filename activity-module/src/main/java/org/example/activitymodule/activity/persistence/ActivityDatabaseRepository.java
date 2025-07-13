package org.example.activitymodule.activity.persistence;

import lombok.RequiredArgsConstructor;
import org.example.activitymodule.activity.domain.Activity;
import org.example.activitymodule.activity.domain.ActivityRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ActivityDatabaseRepository implements ActivityRepository {

    private final ActivityJpaRepository jpaRepository;
    private final ActivityMapper mapper;

    @Override
    public Activity create(Activity activity) {
        var entity = mapper.toEntity(activity);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
}
