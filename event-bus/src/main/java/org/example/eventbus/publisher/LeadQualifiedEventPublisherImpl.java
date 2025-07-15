package org.example.eventbus.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eventbus.LeadQualifiedEventPublisher;
import org.example.eventbus.QualifiedLead;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class LeadQualifiedEventPublisherImpl implements LeadQualifiedEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishLeadQualifiedEvent(final QualifiedLead qualifiedLead) {
        log.info("Publishing lead qualified event :: {}", qualifiedLead.toString());
        applicationEventPublisher.publishEvent(qualifiedLead);
    }
}
