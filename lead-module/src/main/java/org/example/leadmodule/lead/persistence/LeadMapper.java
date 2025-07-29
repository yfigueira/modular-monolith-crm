package org.example.leadmodule.lead.persistence;

import org.example.leadmodule.lead.domain.Lead;
import org.example.leadmodule.lead.domain.LeadState;
import org.mapstruct.*;

import java.util.Arrays;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface LeadMapper {

    Lead toDomain(LeadEntity entity);

    LeadEntity toEntity(Lead domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LeadEntity updateEntity(Lead lead, @MappingTarget LeadEntity entity);

    default LeadState mapState(Integer entity) {
        return Arrays.stream(LeadState.values())
                .filter(v -> v.getCode().equals(entity))
                .findFirst()
                .orElse(LeadState.NOT_AVAILABLE);
    }

    default Integer mapState(LeadState domain) {
        return domain.getCode();
    }
}
