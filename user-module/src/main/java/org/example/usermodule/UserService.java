package org.example.usermodule;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User getById(UUID id);

    List<User> getAll();
}
