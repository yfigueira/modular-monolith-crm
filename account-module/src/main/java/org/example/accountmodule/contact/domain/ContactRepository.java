package org.example.accountmodule.contact.domain;

import org.example.accountmodule.Contact;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepository {

    Contact create(Contact contact);

    Optional<Contact> findById(UUID id);

    List<Contact> findByCompany(UUID companyId);

    Boolean existsByEmail(String email);
}
