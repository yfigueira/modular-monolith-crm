package org.example.accountmodule.contact.web;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.accountmodule.ContactService;
import org.example.accountmodule.contact.web.dto.ContactDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/accounts/contacts")
@Transactional(Transactional.TxType.REQUIRES_NEW)
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    @GetMapping("{id}")
    public ContactDto getById(@PathVariable UUID id) {
        var contact = service.getById(id);
        return ContactDto.mapper().toDto(contact);
    }
}
