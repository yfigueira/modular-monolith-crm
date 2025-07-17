package org.example.leadmodule.event;

import lombok.RequiredArgsConstructor;
import org.example.eventbus.LeadQualifiedEventPublisher;
import org.example.eventbus.QualifiedLead;
import org.example.leadmodule.lead.domain.Lead;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeadEventPublisher {

    private final LeadQualifiedEventPublisher leadQualifiedEventPublisher;

    public void publishLeadQualified(Lead lead) {

        var qualifiedLead = QualifiedLead.builder()
                .id(lead.id())
                .firstName(lead.firstName())
                .lastName(lead.lastName())
                .email(lead.email())
                .build();

        leadQualifiedEventPublisher.publishLeadQualifiedEvent(qualifiedLead);
    }
}
