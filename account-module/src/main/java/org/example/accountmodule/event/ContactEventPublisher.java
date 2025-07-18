package org.example.accountmodule.event;

import lombok.RequiredArgsConstructor;
import org.example.eventbus.ContactCreatedFromLeadEventPublisher;
import org.example.eventbus.ContactFromLead;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContactEventPublisher {

    private final ContactCreatedFromLeadEventPublisher contactCreatedFromLeadEventPublisher;

    public void publishContactCreatedFromLead(UUID leadId, UUID contactId) {
        var contactFromLead = ContactFromLead.builder()
                .leadId(leadId)
                .contactId(contactId)
                .build();

        contactCreatedFromLeadEventPublisher.publishContactCreatedFromLeadEvent(contactFromLead);
    }
}
