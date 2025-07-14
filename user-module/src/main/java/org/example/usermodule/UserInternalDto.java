package org.example.usermodule;

import lombok.Builder;
import org.example.usermodule.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Builder
public record UserInternalDto(
        UUID id,
        String firstName,
        String lastName
) {
    @Mapper
    public interface UserInternalDtoMapper {
        UserInternalDto toDto(User user);
    }

    public static UserInternalDtoMapper mapper() {
        return Mappers.getMapper(UserInternalDtoMapper.class);
    }
}
