package org.example.leadmodule.company.domain;

import lombok.RequiredArgsConstructor;
import org.example.leadmodule.exception.LeadException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    @Override
    public Company create(Company company) {
        if (repository.existsByName(company.name())) {
            throw LeadException.alreadyExists(Company.class, company.name());
        }
        return repository.create(company);
    }

    @Override
    public List<Company> getAll() {
        return repository.findAll();
    }

    @Override
    public Company getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> LeadException.notFound(Company.class, id));
    }
}
