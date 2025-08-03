package org.example.dealmodule.deal.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dealmodule.deal.domain.DealService;
import org.example.dealmodule.deal.web.dto.DealDto;
import org.example.dealmodule.deal.web.dto.DealSummary;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("{id}")
    public DealDto update(
            @PathVariable UUID id,
            @RequestBody @Valid DealDto dto
    ) {
        var deal = DealDto.mapper().toDomain(dto);
        var updated = service.update(id, deal);
        return DealDto.mapper().toDto(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/stage/{stageCode}")
    public ResponseEntity<?> changeStage(
            @PathVariable UUID id,
            @PathVariable Integer stageCode
    ) {
        service.updateStage(id, stageCode);
        return ResponseEntity.ok().build();
    }
}
