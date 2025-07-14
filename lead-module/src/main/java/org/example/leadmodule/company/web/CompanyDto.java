package org.example.leadmodule.company.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.example.leadmodule.common.DtoMapper;
import org.example.leadmodule.company.domain.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Builder
public record CompanyDto(
        UUID id,
        @NotBlank(message = "Name required")
        String name
) {
    @Mapper
    public interface CompanyDtoMapper extends DtoMapper<Company, CompanyDto> {}

    public static CompanyDtoMapper mapper() {
        return Mappers.getMapper(CompanyDtoMapper.class);
    }
}
