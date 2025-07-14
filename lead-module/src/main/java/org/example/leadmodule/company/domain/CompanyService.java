package org.example.leadmodule.company.domain;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    Company create(Company company);

    List<Company> getAll();

    Company getById(UUID id);
}
