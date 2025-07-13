package org.example.accountmodule;

import java.util.List;
import java.util.UUID;

public interface ContactService {

    List<Contact> getByCompany(UUID companyId);
}
