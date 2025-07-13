package org.example.accountmodule.contact.domain;

import org.example.accountmodule.Contact;

import java.util.List;
import java.util.UUID;

public interface ContactRepository {

    List<Contact> findByCompany(UUID companyId);
}
