package org.example.leadmodule.lead.domain;

import lombok.RequiredArgsConstructor;
import org.example.activitymodule.ActivityInternalApi;
import org.example.leadmodule.company.domain.CompanyService;
import org.example.leadmodule.event.LeadEventPublisher;
import org.example.leadmodule.exception.LeadException;
import org.example.leadmodule.jobtitle.domain.LeadJobTitleService;
import org.example.usermodule.UserInternalApi;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class LeadServiceImpl implements LeadService {

    private final LeadRepository repository;
    private final UserInternalApi userInternalApi;
    private final CompanyService companyService;
    private final LeadJobTitleService jobTitleService;
    private final ActivityInternalApi activityInternalApi;
    private final LeadEventPublisher eventPublisher;

    @Override
    public Lead create(Lead lead) {
        if (repository.existsByEmail(lead.email())) {
            throw LeadException.alreadyExists(Lead.class, lead.email());
        }
        return repository.create(lead);
    }

    @Override
    public Lead getById(UUID id) {
        return repository.findById(id)
                .map(this::fetchOwner)
                .map(this::fetchCompany)
                .map(this::fetchJobTitle)
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

    private Lead fetchOwner(Lead lead) {
        var owner = userInternalApi.getInternalById(lead.owner().id());
        return lead.withOwner(owner);
    }

    private Lead fetchCompany(Lead lead) {
        if (lead.company() == null) {
            return lead;
        }
        var company = companyService.getById(lead.company().id());
        return lead.withCompany(company);
    }

    private Lead fetchJobTitle(Lead lead) {
        if (lead.jobTitle() == null) {
            return lead;
        }
        var jobTitle = jobTitleService.getById(lead.jobTitle().id());
        return lead.withJobTitle(jobTitle);
    }

    private Lead fetchActivities(Lead lead) {
        var activities = activityInternalApi.getByEntity(lead.id());
        return lead.withActivities(activities);
    }

    private Boolean isActiveInState(LeadState state) {
        return !(state.equals(LeadState.QUALIFIED) || state.equals(LeadState.DISQUALIFIED));
    }
}
