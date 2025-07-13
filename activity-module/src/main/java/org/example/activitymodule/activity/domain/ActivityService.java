package org.example.activitymodule.activity.domain;

import java.util.List;
import java.util.UUID;

public interface ActivityService {

    Activity create(Activity activity);

    Activity getById(UUID id);

    List<Activity> getAll();
}
