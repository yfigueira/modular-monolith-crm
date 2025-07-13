package org.example.activitymodule.activity.domain;

import org.example.activitymodule.Activity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class ActivityScheduler {

    public static Activity plan(Activity activity) {
        return activity.scheduledAt() != null ? activity : activity.withScheduledAt(defaultDate());
    }

    private static LocalDateTime defaultDate() {
        var nextDay = LocalDate.now().plusDays(1);
        var atNoon = LocalTime.NOON;
        return LocalDateTime.of(nextDay, atNoon);
    }
}
