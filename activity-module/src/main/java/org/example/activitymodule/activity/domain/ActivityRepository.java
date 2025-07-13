package org.example.activitymodule.activity.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository {

    Activity create(Activity activity);

    Optional<Activity> findById(UUID id);

    List<Activity> findAll();
}
