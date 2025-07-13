package org.example.usermodule.user.domain;

import lombok.RequiredArgsConstructor;
import org.example.usermodule.User;
import org.example.usermodule.UserService;
import org.example.usermodule.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserClient client;

    @Override
    public User getById(UUID id) {
        return client.getById(id)
                .orElseThrow(() -> UserException.notFound(User.class, id));
    }

    @Override
    public List<User> getAll() {
        return client.getAll();
    }
}
