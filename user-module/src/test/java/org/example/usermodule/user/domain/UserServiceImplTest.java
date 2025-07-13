package org.example.usermodule.user.domain;

import org.example.usermodule.exception.UserException;
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
class UserServiceImplTest {

    @Mock
    UserClient userClient;

    @InjectMocks
    UserServiceImpl SUT;

    @Test
    void whenUserFound_ShouldReturnUser() {
        // given
        var testId = UUID.randomUUID();
        var testUser = User.builder()
                .id(testId)
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@company.com")
                .phoneNumber("111222333")
                .build();

        Mockito.when(userClient.getById(testId))
                .thenReturn(Optional.of(testUser));

        // when
        var result = SUT.getById(testId);

        // then
        assertThat(result, is(equalTo(testUser)));
    }

    @Test
    void whenUserNotFound_ShouldThrowUserExceptionNotFound() {
        // given
        var unknownId = UUID.randomUUID();
        Mockito.when(userClient.getById(unknownId)).thenThrow(UserException.notFound(User.class, unknownId));

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(UserException.ResourceNotFoundException.class)
                .hasMessage("User not found :: %s".formatted(unknownId));

    }

    @Test
    void debugTest() {
        assertThat(true, is(true));
    }
}