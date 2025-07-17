package org.example.leadmodule.lead.web;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.leadmodule.lead.domain.LeadService;
import org.example.leadmodule.lead.web.dto.LeadDto;
import org.example.leadmodule.lead.web.dto.LeadSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/leads")
@Transactional(Transactional.TxType.REQUIRES_NEW)
@RequiredArgsConstructor
public class LeadController {

    private final LeadService service;

    @PostMapping
    public LeadDto create(@RequestBody @Valid LeadDto dto) {
        var lead = LeadDto.mapper().toDomain(dto);
        var created = service.create(lead);
        return LeadDto.mapper().toDto(created);
    }

    @GetMapping("{id}")
    public LeadDto getById(@PathVariable UUID id) {
        var lead = service.getById(id);
        return LeadDto.mapper().toDto(lead);
    }

    @GetMapping
    public List<LeadSummary> getAll() {
        return service.getAll().stream()
                .map(LeadSummary.mapper()::toDto)
                .toList();
    }

    @PutMapping("{id}")
    public LeadDto update(
            @PathVariable UUID id,
            @RequestBody @Valid LeadDto dto
    ) {
        var lead = LeadDto.mapper().toDomain(dto);
        var updated = service.update(id, lead);
        return LeadDto.mapper().toDto(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/state/{stateCode}")
     public ResponseEntity<?> changeState(
             @PathVariable UUID id,
             @PathVariable Integer stateCode
    ) {
        service.updateState(id, stateCode);
        return ResponseEntity.ok().build();
    }
}
