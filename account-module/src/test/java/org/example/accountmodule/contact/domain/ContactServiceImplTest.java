package org.example.accountmodule.contact.domain;

import org.example.accountmodule.Contact;
import org.example.accountmodule.exception.AccountException;
import org.example.accountmodule.jobtitle.domain.JobTitle;
import org.example.accountmodule.jobtitle.domain.JobTitleService;
import org.example.activitymodule.Activity;
import org.example.activitymodule.ActivityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock
    ContactRepository repository;

    @Mock
    ActivityService activityService;

    @Mock
    JobTitleService jobTitleService;

    @InjectMocks
    ContactServiceImpl SUT;

    @Test
    void whenAlreadyExists_ShouldThrowAccountServiceExceptionAlreadyExists() {
        // given
        var newContact = Contact.builder()
                .email("test@email.com")
                .build();

        Mockito.when(repository.existsByEmail(newContact.email())).thenReturn(true);

        // when, then
        assertThatThrownBy(() -> SUT.create(newContact))
                .isInstanceOf(AccountException.ResourceAlreadyExistsException.class)
                .hasMessage("Contact already exists :: %s".formatted(newContact.email()));
    }

    @Test
    void whenNotAlreadyExists_ShouldReturnCreatedContact() {
        // given
        var newContact = Contact.builder()
                .email("test@email.com")
                .build();

        var createdContact = Contact.builder()
                .id(UUID.randomUUID())
                .email("test@email.com")
                .build();

        Mockito.when(repository.create(newContact)).thenReturn(createdContact);

        // when
        var result = SUT.create(newContact);

        // then
        assertThat(result, is(equalTo(createdContact)));
    }

    @Test
    void whenNotFound_ShouldThrowAccountServiceExceptionNotFound() {
        // given
        var unknownId = UUID.randomUUID();
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(AccountException.ResourceNotFoundException.class)
                .hasMessage("Contact not found :: %s".formatted(unknownId));
    }

    @Test
    void whenFound_ShouldReturnContactWithActivitiesAndJobTitle() {
        // given
        var activities = List.of(
                Activity.builder().subject("Activity 1").build(),
                Activity.builder().subject("Activity 2").build()
        );

        var jobTitleId = UUID.randomUUID();
        var jobTitle = JobTitle.builder()
                .id(jobTitleId)
                .name("Job title")
                .build();

        var contactId = UUID.randomUUID();
        var contact = Contact.builder()
                .id(contactId)
                .firstName("John")
                .lastName("Smith")
                .email("test@email.com")
                .jobTitle(JobTitle.builder().id(jobTitleId).build())
                .build();

        Mockito.when(activityService.getByEntity(contactId)).thenReturn(activities);
        Mockito.when(jobTitleService.getById(jobTitleId)).thenReturn(jobTitle);
        Mockito.when(repository.findById(contactId)).thenReturn(Optional.of(contact));

        // when
        var result = SUT.getById(contactId);

        // then
        assertThat(result,
                is(equalTo(contact
                        .withActivities(activities)
                        .withJobTitle(jobTitle))));
    }
}