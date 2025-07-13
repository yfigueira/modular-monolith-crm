package org.example.accountmodule;

import java.util.List;
import java.util.UUID;

public interface ContactService {

    Contact create(Contact contact);

    Contact getById(UUID id);

    Contact update(UUID id, Contact contact);

    void delete(UUID id);

    List<Contact> getByCompany(UUID companyId);
}
