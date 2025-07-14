package org.example.leadmodule.jobtitle.persistence;

import org.example.leadmodule.jobtitle.domain.JobTitle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface LeadJobTitleMapper {

    JobTitle toDomain(LeadJobTitleEntity entity);

    LeadJobTitleEntity toEntity(JobTitle domain);
}
