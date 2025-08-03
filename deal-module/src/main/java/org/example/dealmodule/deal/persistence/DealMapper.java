package org.example.dealmodule.deal.persistence;

import org.example.dealmodule.deal.domain.Deal;
import org.example.dealmodule.deal.domain.DealStage;
import org.mapstruct.*;

import java.util.Arrays;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface DealMapper {

    Deal toDomain(DealEntity entity);

    DealEntity toEntity(Deal domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DealEntity updateEntity(Deal deal, @MappingTarget DealEntity entity);

    default DealStage mapStage(Integer entity) {
        return Arrays.stream(DealStage.values())
                .filter(v -> v.getCode().equals(entity))
                .findFirst()
                .orElse(DealStage.NOT_AVAILABLE);
    }

    default Integer mapStage(DealStage domain) {
        return domain.getCode();
    }
}
