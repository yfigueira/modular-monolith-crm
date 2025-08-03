package org.example.dealmodule.deal.domain;

import java.util.List;
import java.util.UUID;

public interface DealService {

    List<Deal> getAll();

    Deal getById(UUID id);

    Deal create(Deal deal);

    Deal update(UUID id, Deal deal);

    void delete(UUID id);
}
