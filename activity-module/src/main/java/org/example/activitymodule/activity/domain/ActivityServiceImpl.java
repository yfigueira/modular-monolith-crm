package org.example.activitymodule.activity.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository repository;

    @Override
    public Activity create(Activity activity) {
        var scheduledActivity = ActivityScheduler.plan(activity);
        return repository.create(scheduledActivity);
    }
}
