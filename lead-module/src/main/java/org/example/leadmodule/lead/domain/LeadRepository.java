package org.example.leadmodule.lead.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeadRepository {

    Lead create(Lead lead);

    Optional<Lead> findById(UUID id);

    List<Lead> findAll();

    Boolean existsByEmail(String email);
}
