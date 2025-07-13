package org.example.accountmodule.account.persistence;

import org.example.accountmodule.Contact;
import org.example.accountmodule.account.domain.Account;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface AccountMapper {

    Account toDomain(AccountEntity entity);

    AccountEntity toEntity(Account domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountEntity updateEntity(Account domain, @MappingTarget AccountEntity entity);

    default Contact mapContacts(UUID entity) {
        return Contact.builder().id(entity).build();
    }

    default UUID mapContacts(Contact domain) {
        return domain.id();
    }
}
