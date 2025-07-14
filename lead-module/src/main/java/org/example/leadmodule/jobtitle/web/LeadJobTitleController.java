package org.example.leadmodule.jobtitle.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.leadmodule.jobtitle.domain.LeadJobTitleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leads/job-titles")
@RequiredArgsConstructor
public class LeadJobTitleController {

    private final LeadJobTitleService service;

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
