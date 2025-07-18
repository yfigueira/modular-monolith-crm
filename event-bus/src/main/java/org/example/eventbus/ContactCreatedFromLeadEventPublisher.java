package org.example.eventbus;

public interface ContactCreatedFromLeadEventPublisher {

    void publishContactCreatedFromLeadEvent(final ContactFromLead contactFromLead);
}
