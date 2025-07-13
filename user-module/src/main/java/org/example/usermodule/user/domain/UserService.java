package org.example.usermodule.user.domain;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User getById(UUID id);

    List<User> getAll();
}
