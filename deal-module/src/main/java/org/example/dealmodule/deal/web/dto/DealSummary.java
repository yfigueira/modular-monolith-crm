package org.example.dealmodule.deal.web.dto;

import lombok.Builder;
import org.example.dealmodule.common.DtoMapper;
import org.example.dealmodule.deal.domain.Deal;
import org.example.dealmodule.deal.domain.DealStage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.UUID;

@Builder
public record DealSummary(
        UUID id,
        String title,
        String expectedRevenue,
        Integer stage
) {
    @Mapper
    public interface DealSummaryMapper extends DtoMapper<Deal, DealSummary> {

        default DealStage mapStage(Integer dto) {
            return Arrays.stream(DealStage.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(DealStage.NOT_AVAILABLE);
        }

        default Integer mapStage(DealStage domain) {
            return domain.getCode();
        }
    }

    public static DealSummaryMapper mapper() {
        return Mappers.getMapper(DealSummaryMapper.class);
    }
}
