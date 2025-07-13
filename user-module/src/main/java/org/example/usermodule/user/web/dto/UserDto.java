package org.example.usermodule.user.web.dto;

import lombok.Builder;
import org.example.usermodule.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Builder
public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
    @Mapper
    public interface UserDtoMapper {
        UserDto toDto(User domain);
    }

    public static UserDtoMapper mapper() {
        return Mappers.getMapper(UserDtoMapper.class);
    }
}
