package org.example.activitymodule.activity.web;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.activitymodule.ActivityService;
import org.example.activitymodule.activity.web.dto.ActivityDto;
import org.example.activitymodule.activity.web.dto.ActivitySummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activities")
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class ActivityController {

    private final ActivityService service;

    @PostMapping
    public ActivityDto create(@RequestBody @Valid ActivityDto request) {
        var newActivity = ActivityDto.mapper().toDomain(request);
        var createdActivity = service.create(newActivity);
        return ActivityDto.mapper().toDto(createdActivity);
    }

    @GetMapping("{id}")
    public ActivityDto getById(@PathVariable UUID id) {
        var activity = service.getById(id);
        return ActivityDto.mapper().toDto(activity);
    }

    @GetMapping
    public List<ActivitySummary> getAll() {
        return service.getAll().stream()
                .map(ActivitySummary.mapper()::toDto)
                .toList();
    }

    @PutMapping("{id}")
    public ActivityDto update(
            @PathVariable UUID id,
            @RequestBody @Valid ActivityDto dto
    ) {
        var activity = ActivityDto.mapper().toDomain(dto);
        var updatedActivity = service.update(id, activity);
        return ActivityDto.mapper().toDto(updatedActivity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
