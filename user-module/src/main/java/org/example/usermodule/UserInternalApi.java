package org.example.usermodule;

import java.util.UUID;

public interface UserInternalApi {

    UserInternalDto getInternalById(UUID id);
}
