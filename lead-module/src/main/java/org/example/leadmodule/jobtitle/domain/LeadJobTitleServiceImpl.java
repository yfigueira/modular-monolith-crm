package org.example.leadmodule.jobtitle.domain;

import lombok.RequiredArgsConstructor;
import org.example.leadmodule.exception.LeadException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class LeadJobTitleServiceImpl implements LeadJobTitleService {

    private final LeadJobTitleRepository repository;

    @Override
    public JobTitle create(JobTitle jobTitle) {
        if (repository.existsByName(jobTitle.name())) {
            throw LeadException.alreadyExists(JobTitle.class, jobTitle.name());
        }
        return repository.create(jobTitle);
    }

    @Override
    public List<JobTitle> getAll() {
        return repository.getAll();
    }

    @Override
    public JobTitle getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> LeadException.notFound(JobTitle.class, id));
    }
}
