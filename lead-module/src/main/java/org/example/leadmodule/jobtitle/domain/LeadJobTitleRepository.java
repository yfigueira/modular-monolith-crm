package org.example.leadmodule.jobtitle.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeadJobTitleRepository {

    JobTitle create(JobTitle jobTitle);

    List<JobTitle> getAll();

    Optional<JobTitle> findById(UUID id);

    Boolean existsByName(String name);
}
