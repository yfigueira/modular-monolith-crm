package org.example.leadmodule.lead.web.dto;

import lombok.Builder;
import org.example.leadmodule.common.DtoMapper;
import org.example.leadmodule.lead.domain.Lead;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Builder
public record LeadSummary(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String subject
) {
    @Mapper
    public interface LeadSummaryMapper extends DtoMapper<Lead, LeadSummary> {}

    public static LeadSummaryMapper mapper() {
        return Mappers.getMapper(LeadSummaryMapper.class);
    }
}
