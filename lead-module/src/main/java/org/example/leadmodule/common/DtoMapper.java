package org.example.leadmodule.common;

public interface DtoMapper<DOMAIN, DTO> {

    DOMAIN toDomain(DTO dto);

    DTO toDto(DOMAIN domain);
}
