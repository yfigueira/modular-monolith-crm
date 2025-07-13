package org.example.accountmodule.jobtitle.persistence;

import org.example.accountmodule.jobtitle.domain.JobTitle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface AccJobTitleMapper {

    JobTitle toDomain(JobTitleEntity entity);

    JobTitleEntity toEntity(JobTitle domain);
}
