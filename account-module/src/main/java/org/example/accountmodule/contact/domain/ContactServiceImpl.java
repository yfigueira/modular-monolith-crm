package org.example.accountmodule.contact.domain;

import lombok.RequiredArgsConstructor;
import org.example.accountmodule.Contact;
import org.example.accountmodule.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;

    @Override
    public List<Contact> getByCompany(UUID companyId) {
        return repository.findByCompany(companyId);
    }
}
