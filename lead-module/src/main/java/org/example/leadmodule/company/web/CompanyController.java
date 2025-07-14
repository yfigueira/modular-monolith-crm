package org.example.leadmodule.company.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.leadmodule.company.domain.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/leads/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;

    @PostMapping
    public CompanyDto create(@RequestBody @Valid CompanyDto dto) {
        var company = CompanyDto.mapper().toDomain(dto);
        var created = service.create(company);
        return CompanyDto.mapper().toDto(created);
    }

    @GetMapping
    public List<CompanyDto> getAll() {
        return service.getAll().stream()
                .map(CompanyDto.mapper()::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public CompanyDto getById(@PathVariable UUID id) {
        var company = service.getById(id);
        return CompanyDto.mapper().toDto(company);
    }
}
