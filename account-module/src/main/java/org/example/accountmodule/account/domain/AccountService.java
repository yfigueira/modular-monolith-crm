package org.example.accountmodule.account.domain;

import org.example.accountmodule.contact.domain.Contact;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account create(Account account);

    List<Account> getAll();

    Account getById(UUID id);

    Account update(UUID id, Account account);

    void delete(UUID id);

    Contact addContact(UUID accountId, Contact contact);
}
