package org.example.accountmodule.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.accountmodule.contact.domain.Contact;
import org.example.accountmodule.contact.domain.ContactPriority;
import org.example.accountmodule.contact.domain.ContactService;
import org.example.eventbus.QualifiedLead;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContactEventListener {

    private final ContactService contactService;
    private final ContactEventPublisher contactEventPublisher;

    @EventListener
    public void handleQualifiedLead(QualifiedLead qualifiedLead) {
        var contact = Contact.builder()
                .firstName(qualifiedLead.firstName())
                .lastName(qualifiedLead.lastName())
                .email(qualifiedLead.email())
                .priority(ContactPriority.LOW)
                .build();

        try {
            var newContact = contactService.create(contact);
            log.info("Created contact from lead :: {}", newContact.toString());
            contactEventPublisher.publishContactCreatedFromLead(qualifiedLead.id(), newContact.id());
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
        }
    }
}
