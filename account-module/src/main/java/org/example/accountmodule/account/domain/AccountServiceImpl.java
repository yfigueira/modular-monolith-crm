package org.example.accountmodule.account.domain;

import lombok.RequiredArgsConstructor;
import org.example.accountmodule.contact.domain.Contact;
import org.example.accountmodule.contact.domain.ContactService;
import org.example.accountmodule.exception.AccountException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ContactService contactService;

    @Override
    public Account create(Account account) {
        if (repository.existsByTin(account.tin())) {
            throw AccountException.alreadyExists(Account.class, account.tin());
        }
        return repository.create(account);
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public Account getById(UUID id) {
        return repository.findById(id)
                .map(this::fetchContacts)
                .orElseThrow(() -> AccountException.notFound(Account.class, id));
    }

    @Override
    public Account update(UUID id, Account account) {
        if (!account.id().equals(id)) {
            throw AccountException.actionNotAllowed(Account.class, "id mismatch");
        }
        return repository.update(id, account);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public Contact addContact(UUID accountId, Contact contact) {
        var account = getById(accountId);
        return contactService.create(contact.withCompany(account));
    }

    private Account fetchContacts(Account account) {
        var contacts = contactService.getByCompany(account.id());
        return account.withContacts(contacts);
    }
}
