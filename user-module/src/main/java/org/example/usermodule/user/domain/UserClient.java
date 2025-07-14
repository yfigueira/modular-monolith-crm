package org.example.usermodule.user.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserClient {

    Optional<User> getById(UUID id);

    List<User> getAll();
}
