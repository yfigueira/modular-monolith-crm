package org.example.activitymodule.activity.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActivityType {
    PHONE_CALL(1, "Phone call"),
    EMAIL(2, "Email"),
    MEETING(3, "Meeting"),
    TODO(4, "Todo"),
    NOT_AVAILABLE(0, "N/A");

    private final Integer code;
    private final String label;
}
