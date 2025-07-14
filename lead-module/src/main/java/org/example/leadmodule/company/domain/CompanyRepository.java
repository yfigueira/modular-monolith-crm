package org.example.leadmodule.company.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository {

    Company create(Company company);

    List<Company> findAll();

    Optional<Company> findById(UUID id);

    Boolean existsByName(String name);
}
