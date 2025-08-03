package org.example.dealmodule.deal.domain;

import lombok.RequiredArgsConstructor;
import org.example.activitymodule.ActivityInternalApi;
import org.example.dealmodule.exception.DealException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class DealServiceImpl implements DealService {

    private final DealRepository repository;
    private final ActivityInternalApi activityInternalApi;

    @Override
    public List<Deal> getAll() {
        return repository.findAll();
    }

    @Override
    public Deal getById(UUID id) {
        return repository.findById(id)
                .map(this::fetchActivities)
                .orElseThrow(() -> DealException.notFound(Deal.class, id));
    }

    @Override
    public Deal create(Deal deal) {
        return repository.create(deal.withStage(DealStage.NEW));
    }

    @Override
    public Deal update(UUID id, Deal deal) {
        return repository.update(id, deal);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    private Deal fetchActivities(Deal deal) {
        var activities = activityInternalApi.getByEntity(deal.id());
        return deal.withActivities(activities);
    }
}
