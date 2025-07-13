package org.example.accountmodule.contact.domain;

import lombok.RequiredArgsConstructor;
import org.example.accountmodule.Contact;
import org.example.accountmodule.ContactService;
import org.example.accountmodule.exception.AccountException;
import org.example.accountmodule.jobtitle.domain.JobTitleService;
import org.example.activitymodule.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ActivityService activityService;
    private final JobTitleService jobTitleService;

    @Override
    public Contact create(Contact contact) {
        if (repository.existsByEmail(contact.email())) {
            throw AccountException.alreadyExists(Contact.class, contact.email());
        }
        return repository.create(contact);
    }

    @Override
    public Contact getById(UUID id) {
        return repository.findById(id)
                .map(this::fetchActivities)
                .map(this::fetchJobTitle)
                .orElseThrow(() -> AccountException.notFound(Contact.class, id));
    }

    @Override
    public Contact update(UUID id, Contact contact) {
        if (!contact.id().equals(id)) {
            throw AccountException.actionNotAllowed(Contact.class, "id mismatch");
        }
        return repository.update(id, contact);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<Contact> getByCompany(UUID companyId) {
        return repository.findByCompany(companyId);
    }

    private Contact fetchActivities(Contact contact) {
        var activities = activityService.getByEntity(contact.id());
        return contact.withActivities(activities);
    }

    private Contact fetchJobTitle(Contact contact) {
        var jobTitle = jobTitleService.getById(contact.jobTitle().id());
        return contact.withJobTitle(jobTitle);
    }
}
