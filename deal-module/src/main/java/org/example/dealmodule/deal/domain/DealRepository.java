package org.example.dealmodule.deal.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DealRepository {

    List<Deal> findAll();

    Optional<Deal> findById(UUID id);

    Deal create(Deal deal);

    Deal update(UUID id, Deal deal);

    void delete(UUID id);
}
