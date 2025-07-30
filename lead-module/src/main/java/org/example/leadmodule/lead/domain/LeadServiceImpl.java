package org.example.leadmodule.lead.domain;

import lombok.RequiredArgsConstructor;
import org.example.activitymodule.ActivityInternalApi;
import org.example.leadmodule.event.LeadEventPublisher;
import org.example.leadmodule.exception.LeadException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class LeadServiceImpl implements LeadService {

    private final LeadRepository repository;
    private final ActivityInternalApi activityInternalApi;
    private final LeadEventPublisher eventPublisher;

    @Override
    public Lead create(Lead lead) {
        if (repository.existsByEmail(lead.email())) {
            throw LeadException.alreadyExists(Lead.class, lead.email());
        }

        var leadWithDefaults = lead
                .withState(LeadState.NEW)
                .withIsActive(true);

        return repository.create(leadWithDefaults);
    }

    @Override
    public Lead getById(UUID id) {
        return repository.findById(id)
                .map(this::fetchActivities)
                .orElseThrow(() -> LeadException.notFound(Lead.class, id));
    }

    @Override
    public List<Lead> getAll() {
        return repository.findAll();
    }

    @Override
    public Lead update(UUID id, Lead lead) {
        if (!lead.id().equals(id)) {
            throw LeadException.actionNotAllowed(Lead.class, "id mismatch");
        }
        return repository.update(id, lead);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public void updateState(UUID id, Integer stateCode) {
        var state = Arrays.stream(LeadState.values())
                .filter(s -> s.getCode().equals(stateCode))
                .findFirst()
                .orElseThrow(() -> LeadException.actionNotAllowed(LeadState.class, "invalid state code"));

        if (state.equals(LeadState.NOT_AVAILABLE)) {
            throw LeadException.actionNotAllowed(LeadState.class, "state not assignable");
        }

        var lead = repository.findById(id)
                .map(l -> l.withState(state))
                .map(l -> l.withIsActive(isActiveInState(state)))
                .orElseThrow(() -> LeadException.notFound(Lead.class, id));

        var updatedLead = repository.update(id, lead);

        if (state.equals(LeadState.QUALIFIED)) {
            eventPublisher.publishLeadQualified(updatedLead);
        }
    }

    private Lead fetchActivities(Lead lead) {
        var activities = activityInternalApi.getByEntity(lead.id());
        return lead.withActivities(activities);
    }

    private Boolean isActiveInState(LeadState state) {
        return !(state.equals(LeadState.QUALIFIED) || state.equals(LeadState.DISQUALIFIED));
    }
}
