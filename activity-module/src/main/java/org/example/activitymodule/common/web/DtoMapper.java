package org.example.activitymodule.common.web;

public interface DtoMapper<DOMAIN, DTO> {

    DTO toDto(DOMAIN domain);
    DOMAIN toDomain(DTO dto);
}
