package org.example.usermodule.user.web;

import lombok.RequiredArgsConstructor;
import org.example.usermodule.user.domain.UserService;
import org.example.usermodule.user.web.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserDto> getAll() {
        return service.getAll().stream()
                .map(UserDto.mapper()::toDto)
                .toList();
    }
}
