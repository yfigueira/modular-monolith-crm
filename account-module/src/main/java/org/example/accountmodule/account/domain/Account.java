package org.example.accountmodule.account.domain;

import lombok.Builder;
import lombok.With;
import org.example.accountmodule.contact.domain.Contact;

import java.util.List;
import java.util.UUID;

@Builder
public record Account(
        UUID id,
        String name,
        String country,
        String city,
        String street,
        String streetNumber,
        String zipCode,
        String tin,
        String webUrl,
        @With List<Contact> contacts
) {
}
