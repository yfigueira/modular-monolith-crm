package org.example.accountmodule.contact.domain;

import lombok.RequiredArgsConstructor;
import org.example.accountmodule.exception.AccountException;
import org.example.activitymodule.ActivityInternalApi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ActivityInternalApi activityInternalApi;

    @Override
    public Contact create(Contact contact) {
        if (repository.existsByEmail(contact.email())) {
            throw AccountException.alreadyExists(Contact.class, contact.email());
        }
        return repository.create(contact);
    }

    @Override
    public List<Contact> getAll() {
        return repository.findAll();
    }

    @Override
    public Contact getById(UUID id) {
        return repository.findById(id)
                .map(this::fetchActivities)
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
        var activities = activityInternalApi.getByEntity(contact.id());
        return contact.withActivities(activities);
    }
}
