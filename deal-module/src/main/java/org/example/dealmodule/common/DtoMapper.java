package org.example.dealmodule.common;

public interface DtoMapper<DOMAIN, DTO> {

    DOMAIN toDomain(DTO dto);

    DTO toDto(DOMAIN domain);
}
