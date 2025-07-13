package org.example.accountmodule.account.persistence;

import org.example.accountmodule.Contact;
import org.example.accountmodule.account.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface AccountMapper {

    Account toDomain(AccountEntity entity);

    AccountEntity toEntity(Account domain);

    default Contact mapContacts(UUID entity) {
        return Contact.builder().id(entity).build();
    }

    default UUID mapContacts(Contact domain) {
        return domain.id();
    }
}
