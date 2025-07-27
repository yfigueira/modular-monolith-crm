package org.example.accountmodule.contact.persistence;

import org.example.accountmodule.contact.domain.Contact;
import org.example.accountmodule.account.domain.Account;
import org.example.accountmodule.contact.domain.ContactPriority;
import org.example.accountmodule.jobtitle.domain.JobTitle;
import org.mapstruct.*;

import java.util.Arrays;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ContactMapper {

    Contact toDomain(ContactEntity entity);

    ContactEntity toEntity(Contact contact);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContactEntity updateEntity(Contact contact, @MappingTarget ContactEntity entity);

    default ContactPriority mapContactPriority(Integer entity) {
        return Arrays.stream(ContactPriority.values())
                .filter(v -> v.getCode().equals(entity))
                .findFirst()
                .orElse(ContactPriority.LOW);
    }

    default Integer mapContactPriority(ContactPriority domain) {
        return domain.getCode();
    }

    default Account mapAccount(UUID entity) {
        return entity == null ? null : Account.builder().id(entity).build();
    }

    default UUID mapAccount(Account domain) {
        return domain == null ? null : domain.id();
    }
}
