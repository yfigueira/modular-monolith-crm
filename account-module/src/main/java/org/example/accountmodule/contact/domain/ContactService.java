package org.example.accountmodule.contact.domain;

import java.util.List;
import java.util.UUID;

public interface ContactService {

    Contact create(Contact contact);

    List<Contact> getAll();

    Contact getById(UUID id);

    Contact update(UUID id, Contact contact);

    void delete(UUID id);

    List<Contact> getByCompany(UUID companyId);
}
