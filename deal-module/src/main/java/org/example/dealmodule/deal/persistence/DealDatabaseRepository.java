package org.example.dealmodule.deal.persistence;

import lombok.RequiredArgsConstructor;
import org.example.dealmodule.deal.domain.Deal;
import org.example.dealmodule.deal.domain.DealRepository;
import org.example.dealmodule.exception.DealException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class DealDatabaseRepository implements DealRepository {

    private final DealJpaRepository jpaRepository;
    private final DealMapper mapper;

    @Override
    public List<Deal> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Deal> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Deal create(Deal deal) {
        var entity = mapper.toEntity(deal);
        var created = jpaRepository.save(entity);
        return mapper.toDomain(created);
    }

    @Override
    public Deal update(UUID id, Deal deal) {
        return jpaRepository.findById(id)
                .map(entity -> mapper.updateEntity(deal, entity))
                .map(jpaRepository::save)
                .map(mapper::toDomain)
                .orElseThrow(() -> DealException.notFound(Deal.class, id));
    }

    @Override
    public void delete(UUID id) {
        var entity = jpaRepository.findById(id)
                .orElseThrow(() -> DealException.notFound(Deal.class, id));
        jpaRepository.delete(entity);
    }
}
