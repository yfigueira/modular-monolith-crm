package org.example.accountmodule.jobtitle.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.accountmodule.jobtitle.domain.JobTitleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts/job-titles")
@RequiredArgsConstructor
public class JobTitleController {

    private final JobTitleService service;

    @PostMapping
    public JobTitleDto create(@RequestBody @Valid JobTitleDto dto) {
        var jobTitle = JobTitleDto.mapper().toDomain(dto);
        var created = service.create(jobTitle);
        return JobTitleDto.mapper().toDto(created);
    }

    @GetMapping
    public List<JobTitleDto> getAll() {
        return service.getAll().stream()
                .map(JobTitleDto.mapper()::toDto)
                .toList();
    }
}
