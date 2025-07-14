package org.example.accountmodule.contact.web.dto;

import lombok.Builder;
import org.example.accountmodule.contact.domain.Contact;
import org.example.accountmodule.common.web.DtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Builder
public record ContactSummary(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
    @Mapper
    public interface ContactSummaryMapper extends DtoMapper<Contact, ContactSummary> {}

    public static ContactSummaryMapper mapper() {
        return Mappers.getMapper(ContactSummaryMapper.class);
    }
}