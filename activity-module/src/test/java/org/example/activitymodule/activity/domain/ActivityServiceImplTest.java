package org.example.activitymodule.activity.domain;

import org.example.activitymodule.exception.ActivityException;
import org.example.usermodule.UserInternalApi;
import org.example.usermodule.UserInternalDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

    @Mock
    ActivityRepository repository;

    @Mock
    UserInternalApi userInternalApi;

    @InjectMocks
    ActivityServiceImpl SUT;


    @Test
    void whenActivityFound_ShouldHaveOwner() {
        // given
        var userId = UUID.randomUUID();
        var user = UserInternalDto.builder()
                .id(userId)
                .firstName("Mock")
                .lastName("User")
                .build();

        var activityId = UUID.randomUUID();
        var activity = Activity.builder()
                .id(activityId)
                .subject("Test activity")
                .type(ActivityType.PHONE_CALL)
                .entityType(EntityType.LEAD)
                .status(ActivityStatus.OPEN)
                .owner(UserInternalDto.builder().id(userId).build())
                .build();

        Mockito.when(userInternalApi.getInternalById(userId)).thenReturn(user);
        Mockito.when(repository.findById(activityId)).thenReturn(Optional.of(activity));

        // when
        var result = SUT.getById(activityId);

        // then
        assertThat(result.owner(), is(equalTo(user)));
    }

    @Test
    void whenActivityNotFound_ShouldThrowActivityExceptionNotFound() {
        // given
        var unknownId = UUID.randomUUID();
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(ActivityException.ResourceNotFoundException.class)
                .hasMessage("Activity not found :: %s".formatted(unknownId));
    }

    @Test
    void whenIdMismatchOnUpdate_ShouldThrowActivityExceptionActionNotAllowed() {
        // given
        var argumentId = UUID.randomUUID();
        var activityId = UUID.randomUUID();
        var activity = Activity.builder()
                .id(activityId)
                .build();

        // when, then
        assertThatThrownBy(() -> SUT.update(argumentId, activity))
                .isInstanceOf(ActivityException.ActionNotAllowedException.class)
                .hasMessage("Action on Activity not allowed :: id mismatch");
    }
}