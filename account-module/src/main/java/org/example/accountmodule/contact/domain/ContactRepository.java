package org.example.accountmodule.contact.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepository {

    Contact create(Contact contact);

    List<Contact> findAll();

    Optional<Contact> findById(UUID id);

    Contact update(UUID id, Contact contact);

    void delete(UUID id);

    List<Contact> findByCompany(UUID companyId);

    Boolean existsByEmail(String email);
}
