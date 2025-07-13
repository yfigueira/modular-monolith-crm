package org.example.accountmodule.contact.domain;

import org.example.accountmodule.Contact;
import org.example.accountmodule.exception.AccountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock
    ContactRepository repository;

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
}