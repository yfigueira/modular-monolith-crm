package org.example.accountmodule.contact.persistence;

import lombok.RequiredArgsConstructor;
import org.example.accountmodule.Contact;
import org.example.accountmodule.contact.domain.ContactRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class ContactDatabaseRepository implements ContactRepository {

    private final ContactJpaRepository jpaRepository;
    private final ContactMapper mapper;

    @Override
    public List<Contact> findByCompany(UUID companyId) {
        return jpaRepository.findByCompany(companyId).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
