package org.example.leadmodule.jobtitle.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.example.leadmodule.common.DtoMapper;
import org.example.leadmodule.jobtitle.domain.JobTitle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Builder
public record JobTitleDto(
        UUID id,
        @NotBlank(message = "Name required")
        String name
) {
    @Mapper
    public interface JobTitleDtoMapper extends DtoMapper<JobTitle, JobTitleDto> {}

    public static JobTitleDtoMapper mapper() {
        return Mappers.getMapper(JobTitleDtoMapper.class);
    }
}
