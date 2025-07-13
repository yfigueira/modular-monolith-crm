package org.example.accountmodule.account.web;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.accountmodule.account.domain.AccountService;
import org.example.accountmodule.account.web.dto.AccountDto;
import org.example.accountmodule.account.web.dto.AccountSummary;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AccountController {

    private final AccountService service;

    @PostMapping
    public AccountDto create(@RequestBody @Valid AccountDto dto) {
        var account = AccountDto.mapper().toDomain(dto);
        var created = service.create(account);
        return AccountDto.mapper().toDto(created);
    }

    @GetMapping
    public List<AccountSummary> getAll() {
        return service.getAll().stream()
                .map(AccountSummary.mapper()::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public AccountDto getById(@PathVariable UUID id) {
        var account = service.getById(id);
        return AccountDto.mapper().toDto(account);
    }
}
