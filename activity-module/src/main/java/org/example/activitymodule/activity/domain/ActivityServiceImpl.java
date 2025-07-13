package org.example.activitymodule.activity.domain;

import lombok.RequiredArgsConstructor;
import org.example.activitymodule.activity.exception.ActivityException;
import org.example.usermodule.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository repository;
    private final UserService userService;

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

    private Activity fetchOwner(Activity activity) {
        var owner = userService.getById(activity.owner().id());
        return activity.withOwner(owner);
    }
}
