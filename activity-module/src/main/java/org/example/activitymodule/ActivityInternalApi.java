package org.example.activitymodule;

import java.util.List;
import java.util.UUID;

public interface ActivityInternalApi {

    List<ActivityInternalDto> getByEntity(UUID entityId);
}
