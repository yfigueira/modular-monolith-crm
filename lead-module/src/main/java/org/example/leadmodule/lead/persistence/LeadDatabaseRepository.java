package org.example.leadmodule.lead.persistence;

import lombok.RequiredArgsConstructor;
import org.example.leadmodule.exception.LeadException;
import org.example.leadmodule.lead.domain.Lead;
import org.example.leadmodule.lead.domain.LeadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class LeadDatabaseRepository implements LeadRepository {

    private final LeadJpaRepository jpaRepository;
    private final LeadMapper mapper;

    @Override
    public Lead create(Lead lead) {
        var entity = mapper.toEntity(lead);
        var created = jpaRepository.save(entity);
        return mapper.toDomain(created);
    }

    @Override
    public Optional<Lead> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Lead> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Lead update(UUID id, Lead lead) {
        return jpaRepository.findById(id)
                .map(entity -> mapper.updateEntity(lead, entity))
                .map(jpaRepository::save)
                .map(mapper::toDomain)
                .orElseThrow(() -> LeadException.notFound(Lead.class, id));
    }

    @Override
    public void delete(UUID id) {
        var entity = jpaRepository.findById(id)
                .orElseThrow(() -> LeadException.notFound(Lead.class, id));
        jpaRepository.delete(entity);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}
