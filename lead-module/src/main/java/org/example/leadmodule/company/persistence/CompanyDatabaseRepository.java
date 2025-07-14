package org.example.leadmodule.company.persistence;

import lombok.RequiredArgsConstructor;
import org.example.leadmodule.company.domain.Company;
import org.example.leadmodule.company.domain.CompanyRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class CompanyDatabaseRepository implements CompanyRepository {

    private final CompanyJpaRepository jpaRepository;
    private final CompanyMapper mapper;

    @Override
    public Company create(Company company) {
        var entity = mapper.toEntity(company);
        var created = jpaRepository.save(entity);
        return mapper.toDomain(created);
    }

    @Override
    public List<Company> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Company> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }
}
