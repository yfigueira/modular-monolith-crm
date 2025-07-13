package org.example.accountmodule.account.web.dto;

import lombok.Builder;
import org.example.accountmodule.account.domain.Account;
import org.example.accountmodule.common.web.DtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Builder
public record AccountSummary(
        UUID id,
        String name,
        String country,
        String city
) {
    @Mapper
    public interface AccountSummaryMapper extends DtoMapper<Account, AccountSummary> {}

    public static AccountSummaryMapper mapper() {
        return Mappers.getMapper(AccountSummaryMapper.class);
    }
}
