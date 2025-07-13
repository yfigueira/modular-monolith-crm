package org.example.usermodule.user.domain;

import org.example.usermodule.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserClient {

    Optional<User> getById(UUID id);

    List<User> getAll();
}
