package org.example.leadmodule.lead.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LeadState {
    NEW(1, "New"),
    CONTACT_ATTEMPT(2, "Contact attempt"),
    CONTACTED(3, "Contacted"),
    DISQUALIFIED(4, "Disqualified"),
    QUALIFIED(5, "Qualified"),
    NOT_AVAILABLE(0, "N/A");

    private final Integer code;
    private final String label;
}
