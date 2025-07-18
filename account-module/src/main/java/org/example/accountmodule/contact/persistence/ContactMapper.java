package org.example.accountmodule.contact.persistence;

import org.example.accountmodule.contact.domain.Contact;
import org.example.accountmodule.account.domain.Account;
import org.example.accountmodule.contact.domain.ContactPriority;
import org.example.accountmodule.jobtitle.domain.JobTitle;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ContactMapper {

    Contact toDomain(ContactEntity entity);

    ContactEntity toEntity(Contact contact);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContactEntity updateEntity(Contact contact, @MappingTarget ContactEntity entity);

    default Integer mapContactPriority(ContactPriority domain) {
        return domain.getCode();
    }

    default JobTitle mapJobTitle(UUID entity) {
        return JobTitle.builder().id(entity).build();
    }

    default UUID mapJobTitle(JobTitle domain) {
        return domain.id();
    }

    default Account mapAccount(UUID entity) {
        return Account.builder().id(entity).build();
    }

    default UUID mapAccount(Account domain) {
        return domain.id();
    }
}
