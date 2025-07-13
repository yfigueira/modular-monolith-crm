package org.example.accountmodule.jobtitle.domain;

import java.util.List;
import java.util.UUID;

public interface JobTitleService {

    JobTitle create(JobTitle jobTitle);

    List<JobTitle> getAll();

    JobTitle getById(UUID id);
}
