package org.example.leadmodule.lead.web.dto;

import lombok.Builder;
import org.example.leadmodule.common.DtoMapper;
import org.example.leadmodule.lead.domain.Lead;
import org.example.leadmodule.lead.domain.LeadState;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.UUID;

@Builder
public record LeadSummary(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String subject,
        Integer state
) {
    @Mapper
    public interface LeadSummaryMapper extends DtoMapper<Lead, LeadSummary> {

        default Integer mapLeadState(LeadState domain) {
            return domain.getCode();
        }

        default LeadState mapState(Integer dto) {
            return Arrays.stream(LeadState.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(LeadState.NOT_AVAILABLE);
        }
    }

    public static LeadSummaryMapper mapper() {
        return Mappers.getMapper(LeadSummaryMapper.class);
    }
}
