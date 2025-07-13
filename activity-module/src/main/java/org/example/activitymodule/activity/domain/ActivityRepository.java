package org.example.activitymodule.activity.domain;

import org.example.activitymodule.Activity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository {

    Activity create(Activity activity);

    Optional<Activity> findById(UUID id);

    List<Activity> findAll();

    Activity update(UUID id, Activity activity);
}
