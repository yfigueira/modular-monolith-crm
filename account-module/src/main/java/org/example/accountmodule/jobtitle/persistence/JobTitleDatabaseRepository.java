package org.example.accountmodule.jobtitle.persistence;

import lombok.RequiredArgsConstructor;
import org.example.accountmodule.jobtitle.domain.JobTitle;
import org.example.accountmodule.jobtitle.domain.JobTitleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class JobTitleDatabaseRepository implements JobTitleRepository {

    private final JobTitleJpaRepository jpaRepository;
    private final AccJobTitleMapper mapper;

    @Override
    public JobTitle create(JobTitle jobTitle) {
        var entity = mapper.toEntity(jobTitle);
        var createdEntity = jpaRepository.save(entity);
        return mapper.toDomain(createdEntity);
    }

    @Override
    public List<JobTitle> getAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<JobTitle> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }
}
