package org.example.activitymodule.activity.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActivityStatus {
    OPEN(1),
    CLOSE(2),
    NOT_AVAILABLE(0);

    private final Integer code;
}
