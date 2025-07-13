package org.example.activitymodule.activity.web;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.activitymodule.activity.domain.ActivityService;
import org.example.activitymodule.activity.web.dto.ActivityDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
