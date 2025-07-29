package org.example.accountmodule.contact.web;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.accountmodule.contact.domain.ContactService;
import org.example.accountmodule.contact.web.dto.ContactDto;
import org.example.accountmodule.contact.web.dto.ContactSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts/contacts")
@Transactional(Transactional.TxType.REQUIRES_NEW)
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    @GetMapping
    public List<ContactSummary> getAll() {
        return service.getAll().stream()
                .map(ContactSummary.mapper()::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public ContactDto getById(@PathVariable UUID id) {
        var contact = service.getById(id);
        return ContactDto.mapper().toDto(contact);
    }

    @PutMapping("{id}")
    public ContactDto update(
            @PathVariable UUID id,
            @RequestBody @Valid ContactDto dto
    ) {
        var contact = ContactDto.mapper().toDomain(dto);
        var updated = service.update(id, contact);
        return ContactDto.mapper().toDto(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
