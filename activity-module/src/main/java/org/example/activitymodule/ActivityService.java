package org.example.activitymodule;

import java.util.List;
import java.util.UUID;

public interface ActivityService {

    Activity create(Activity activity);

    Activity getById(UUID id);

    List<Activity> getAll();

    Activity update(UUID id, Activity activity);

    void delete(UUID id);

    List<Activity> getByEntity(UUID entityId);
}
