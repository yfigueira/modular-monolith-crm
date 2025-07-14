package org.example.activitymodule.activity.domain;

import lombok.RequiredArgsConstructor;
import org.example.activitymodule.ActivityInternalApi;
import org.example.activitymodule.ActivityInternalDto;
import org.example.activitymodule.exception.ActivityException;
import org.example.usermodule.UserInternalApi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService, ActivityInternalApi {

    private final ActivityRepository repository;
    private final UserInternalApi userInternalApi;

    @Override
    public Activity create(Activity activity) {
        var scheduledActivity = ActivityScheduler.plan(activity);
        return repository.create(scheduledActivity);
    }

    @Override
    public Activity getById(UUID id) {
        return repository.findById(id)
                .map(this::fetchOwner)
                .orElseThrow(() -> ActivityException.notFound(Activity.class, id));
    }

    @Override
    public List<Activity> getAll() {
        return repository.findAll();
    }

    @Override
    public Activity update(UUID id, Activity activity) {
        if (!activity.id().equals(id)) {
            throw ActivityException.actionNotAllowed(Activity.class, "id mismatch");
        }
        return repository.update(id, activity);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<ActivityInternalDto> getByEntity(UUID entityId) {
        return repository.findByEntity(entityId).stream()
                .map(ActivityInternalDto.mapper()::toDto)
                .toList();
    }

    private Activity fetchOwner(Activity activity) {
        var owner = userInternalApi.getInternalById(activity.owner().id());
        return activity.withOwner(owner);
    }
}
