package org.example.leadmodule.lead.domain;

import org.example.leadmodule.event.LeadEventPublisher;
import org.example.leadmodule.exception.LeadException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class LeadServiceImplTest {

    @Mock
    LeadRepository repository;

    @Mock
    LeadEventPublisher eventPublisher;

    @InjectMocks
    LeadServiceImpl SUT;

    @Test
    void whenEmailAlreadyExists_ShouldThrowLeadExceptionAlreadyExists() {
        // given
        var newLead = Lead.builder()
                .email("already.exists@email.com")
                .build();

        Mockito.when(repository.existsByEmail("already.exists@email.com")).thenReturn(true);

        // when, then
        assertThatThrownBy(() -> SUT.create(newLead))
                .isInstanceOf(LeadException.ResourceAlreadyExistsException.class)
                .hasMessage("Lead already exists :: %s".formatted(newLead.email()));
    }

    @Test
    void whenNotEmailAlreadyExists_ShouldReturnCreatedLeadWithStateNewAndIsActiveTrue() {
        // given
        var newLead = Lead.builder()
                .email("valid@email.com")
                .build();

        var createdLead = Lead.builder()
                .id(UUID.randomUUID())
                .email("valid@email.com")
                .state(LeadState.NEW)
                .isActive(true)
                .build();

        Mockito.when(repository.existsByEmail(newLead.email())).thenReturn(false);
        Mockito.when(repository.create(newLead.withIsActive(true).withState(LeadState.NEW))).thenReturn(createdLead);

        // when
        var result = SUT.create(newLead);

        // then
        assertThat(result, is(equalTo(createdLead)));
    }

    @Test
    void whenNotFound_ShouldThrowLeadExceptionNotFound() {
        // given
        var unknownId = UUID.randomUUID();
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(LeadException.ResourceNotFoundException.class)
                .hasMessage("Lead not found :: %s".formatted(unknownId));
    }

    @Test
    void whenIdMismatchOnUpdate_ShouldThrowLeadExceptionActionNotAllowed() {
        // given
        var argumentId = UUID.randomUUID();
        var leadId = UUID.randomUUID();
        var lead = Lead.builder()
                .id(leadId)
                .build();

        // when, then
        assertThatThrownBy(() -> SUT.update(argumentId, lead))
                .isInstanceOf(LeadException.ActionNotAllowedException.class)
                .hasMessage("Action on Lead not allowed :: id mismatch");
    }

    @Test
    void whenUpdateStateIsUnknownState_ShouldThrowLeadExceptionActionNotAllowed() {
        // given
        var unknownStateCode = -1;
        var leadId = UUID.randomUUID();

        // when, then
        assertThatThrownBy(() -> SUT.updateState(leadId, unknownStateCode))
                .isInstanceOf(LeadException.ActionNotAllowedException.class)
                .hasMessage("Action on LeadState not allowed :: invalid state code");
    }

    @Test
    void whenUpdateStateIsNotAvailableState_ShouldThrowLeadExceptionActionNotAllowed() {
        // given
        var notAvailableStateCode = 0;
        var leadId = UUID.randomUUID();

        // when, then
        assertThatThrownBy(() -> SUT.updateState(leadId, notAvailableStateCode))
                .isInstanceOf(LeadException.ActionNotAllowedException.class)
                .hasMessage("Action on LeadState not allowed :: state not assignable");
    }

    @ParameterizedTest
    @MethodSource("leadStateTestArguments")
    void whenStateCodeValid_ShouldPerformStateChange(
            LeadState newState,
            boolean activeInNewState,
            int publishedTimes
    ) {
        // given
        var newStateCode = newState.getCode();

        var leadId = UUID.randomUUID();
        var lead = Lead.builder().state(newState).build();
        var updatedLead = lead.withState(newState).withIsActive(activeInNewState);

        Mockito.when(repository.findById(leadId)).thenReturn(Optional.of(lead));
        Mockito.when(repository.update(leadId, updatedLead)).thenReturn(updatedLead);

        // when
        SUT.updateState(leadId, newStateCode);

        // then
        Mockito.verify(eventPublisher, times(publishedTimes)).publishLeadQualified(updatedLead);
    }

    private static Stream<Arguments> leadStateTestArguments() {
        return Stream.of(
                Arguments.of(LeadState.NEW, true, 0),
                Arguments.of(LeadState.CONTACT_ATTEMPT, true, 0),
                Arguments.of(LeadState.CONTACTED, true, 0),
                Arguments.of(LeadState.DISQUALIFIED, false, 0),
                Arguments.of(LeadState.QUALIFIED, false, 1)
        );
    }
}