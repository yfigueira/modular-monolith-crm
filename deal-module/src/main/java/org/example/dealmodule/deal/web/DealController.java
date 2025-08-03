package org.example.dealmodule.deal.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dealmodule.deal.domain.DealService;
import org.example.dealmodule.deal.web.dto.DealDto;
import org.example.dealmodule.deal.web.dto.DealSummary;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService service;

    @GetMapping
    public List<DealSummary> getAll() {
        return service.getAll().stream()
                .map(DealSummary.mapper()::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public DealDto getById(@PathVariable UUID id) {
        var deal = service.getById(id);
        return DealDto.mapper().toDto(deal);
    }

    @PostMapping
    public DealDto create(@RequestBody @Valid DealDto dto) {
        var deal = DealDto.mapper().toDomain(dto);
        var created = service.create(deal);
        return DealDto.mapper().toDto(created);
    }
}
