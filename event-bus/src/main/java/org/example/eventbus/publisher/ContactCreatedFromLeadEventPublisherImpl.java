package org.example.eventbus.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eventbus.ContactCreatedFromLeadEventPublisher;
import org.example.eventbus.ContactFromLead;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class ContactCreatedFromLeadEventPublisherImpl implements ContactCreatedFromLeadEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishContactCreatedFromLeadEvent(final ContactFromLead contactFromLead) {
        log.info("Publishing contact created from lead event :: {}", contactFromLead.toString());
        applicationEventPublisher.publishEvent(contactFromLead);
    }
}
