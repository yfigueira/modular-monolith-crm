package org.example.activitymodule.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.activitymodule.activity.domain.ActivityService;
import org.example.eventbus.ContactFromLead;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ActivityEventListener {

    private final ActivityService activityService;

    @EventListener
    public void handleContactCreatedFromLead(ContactFromLead contactFromLead) {
        log.info("Reassigning activities from {} to {}", contactFromLead.leadId(), contactFromLead.contactId());
        activityService.changeEntity(contactFromLead.leadId(), contactFromLead.contactId());
    }
}
