package org.example.accountmodule;

import lombok.Builder;
import org.example.accountmodule.common.web.DtoMapper;
import org.example.accountmodule.contact.domain.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Builder
public record ContactInternalDto(
        UUID id,
        String firstName,
        String lastName,
        String email
) {
    @Mapper
    public interface ContactInternalDtoMapper extends DtoMapper<Contact, ContactInternalDto> {}

    public static ContactInternalDtoMapper mapper() {
        return Mappers.getMapper(ContactInternalDtoMapper.class);
    }
}
