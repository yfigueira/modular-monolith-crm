package org.example.activitymodule.activity.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityType {
    LEAD(1, "Lead"),
    CONTACT(2, "Contact"),
    DEAL(3, "Deal"),
    NOT_AVAILABLE(0, "N/A");

    private final Integer code;
    private final String label;
}
