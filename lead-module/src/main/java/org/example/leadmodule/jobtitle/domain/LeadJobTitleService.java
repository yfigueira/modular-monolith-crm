package org.example.leadmodule.jobtitle.domain;

import java.util.List;
import java.util.UUID;

public interface LeadJobTitleService {

    JobTitle create(JobTitle jobTitle);

    List<JobTitle> getAll();

    JobTitle getById(UUID id);
}
