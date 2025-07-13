package org.example.accountmodule.contact.persistence;

import org.example.accountmodule.Contact;
import org.example.accountmodule.account.domain.Account;
import org.example.accountmodule.contact.domain.ContactPriority;
import org.example.accountmodule.jobtitle.domain.JobTitle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ContactMapper {

    Contact toDomain(ContactEntity entity);

    ContactEntity toEntity(Contact contact);

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
