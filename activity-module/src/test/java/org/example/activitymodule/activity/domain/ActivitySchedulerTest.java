package org.example.activitymodule.activity.domain;

import org.example.activitymodule.Activity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

class ActivitySchedulerTest {

    @Test
    void whenActivityIsScheduled_ShouldReturnActivity() {
        // given
        var activityDate = LocalDateTime.now();
        var scheduledActivity = Activity.builder()
                .subject("Scheduled activity")
                .scheduledAt(activityDate)
                .build();

        // when
        var result = ActivityScheduler.plan(scheduledActivity);

        // then
        assertThat(result.scheduledAt(), is(not(nullValue())));
        assertThat(result.scheduledAt(), is(equalTo(scheduledActivity.scheduledAt())));
    }

    @Test
    void whenActivityIsNotScheduled_ShouldReturnActivityWithDefaultDate() {
        // given
        var nextDayAtNoon = LocalDateTime.of(
                LocalDate.now().plusDays(1),
                LocalTime.NOON
        );
        var notScheduledActivity = Activity.builder()
                .subject("Not scheduled activity")
                .build();

        // when
        var result = ActivityScheduler.plan(notScheduledActivity);

        // then
        assertThat(result.scheduledAt(), is(not(nullValue())));
        assertThat(result.scheduledAt(), is(equalTo(nextDayAtNoon)));
    }
}