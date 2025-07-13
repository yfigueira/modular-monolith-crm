package org.example.activitymodule.user.web;

import lombok.Builder;
import org.example.activitymodule.common.web.DtoMapper;
import org.example.activitymodule.user.domain.User;
import org.mapstruct.Mapper;

import java.util.UUID;

@Builder
public record UserDto(
        UUID id,
        String firstName,
        String lastName
) {
    @Mapper
    public interface UserDtoMapper extends DtoMapper<User, UserDto> {}
}
