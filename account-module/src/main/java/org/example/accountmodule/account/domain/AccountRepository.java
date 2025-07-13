package org.example.accountmodule.account.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    Account create(Account account);

    List<Account> findAll();

    Optional<Account> findById(UUID id);

    Account update(UUID id, Account account);

    void delete(UUID id);

    Boolean existsByTin(String tin);
}
