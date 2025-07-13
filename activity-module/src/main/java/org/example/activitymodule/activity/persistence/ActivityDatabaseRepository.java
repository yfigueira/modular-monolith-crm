package org.example.activitymodule.activity.persistence;

import lombok.RequiredArgsConstructor;
import org.example.activitymodule.activity.domain.Activity;
import org.example.activitymodule.activity.domain.ActivityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<Activity> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Activity> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
