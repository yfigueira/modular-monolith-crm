package org.example.leadmodule.lead.persistence;

import org.example.leadmodule.company.domain.Company;
import org.example.leadmodule.jobtitle.domain.JobTitle;
import org.example.leadmodule.lead.domain.Lead;
import org.example.leadmodule.lead.domain.LeadState;
import org.example.usermodule.UserInternalDto;
import org.mapstruct.*;

import java.util.Arrays;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface LeadMapper {

    Lead toDomain(LeadEntity entity);

    LeadEntity toEntity(Lead domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LeadEntity updateEntity(Lead lead, @MappingTarget LeadEntity entity);

    default UserInternalDto mapOwner(UUID entity) {
        return UserInternalDto.builder().id(entity).build();
    }

    default UUID mapOwner(UserInternalDto domain) {
        return domain.id();
    }

    default Company mapCompany(UUID entity) {
        return entity == null ? null : Company.builder().id(entity).build();
    }

    default UUID mapCompany(Company domain) {
        return domain == null ? null : domain.id();
    }

    default JobTitle mapJobTitle(UUID entity) {
        return entity == null ? null : JobTitle.builder().id(entity).build();
    }

    default UUID mapJobTitle(JobTitle domain) {
        return domain == null ? null : domain.id();
    }

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
