package org.example.accountmodule.account.domain;

import org.example.accountmodule.contact.domain.Contact;
import org.example.accountmodule.contact.domain.ContactService;
import org.example.accountmodule.exception.AccountException;
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
class AccountServiceImplTest {

    @Mock
    AccountRepository repository;

    @Mock
    ContactService contactService;

    @InjectMocks
    AccountServiceImpl SUT;

    @Test
    void whenAlreadyExists_ShouldThrowAccountExceptionAlreadyExists() {
        // given
        var newAccount = Account.builder()
                .tin("0000000000")
                .build();

        Mockito.when(repository.existsByTin(newAccount.tin())).thenReturn(true);

        // when, then
        assertThatThrownBy(() -> SUT.create(newAccount))
                .isInstanceOf(AccountException.ResourceAlreadyExistsException.class)
                .hasMessage("Account already exists :: %s".formatted(newAccount.tin()));
    }

    @Test
    void whenNotAlreadyExists_ShouldReturnCreatedAccount() {
        // given
        var newAccount = Account.builder()
                .tin("0000000000")
                .build();

        var createdAccount = Account.builder()
                .id(UUID.randomUUID())
                .tin("0000000000")
                .build();

        Mockito.when(repository.create(newAccount)).thenReturn(createdAccount);

        // when
        var result = SUT.create(newAccount);

        // then
        assertThat(result, is(equalTo(createdAccount)));
    }

    @Test
    void whenNotFound_ShouldThrowAccountExceptionNotFound() {
        // given
        var unknownId = UUID.randomUUID();
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(AccountException.ResourceNotFoundException.class)
                .hasMessage("Account not found :: %s".formatted(unknownId));
    }

    @Test
    void whenFound_ShouldReturnAccountWithContacts() {
        // given
        var accountId = UUID.randomUUID();
        var contacts = List.of(
                Contact.builder().id(UUID.randomUUID()).firstName("John").lastName("Smith").build(),
                Contact.builder().id(UUID.randomUUID()).firstName("Jane").lastName("Doe").build()
        );
        var account = Account.builder()
                .id(accountId)
                .name("Company")
                .country("Country")
                .city("City")
                .tin("0000000000")
                .build();

        Mockito.when(contactService.getByCompany(accountId)).thenReturn(contacts);
        Mockito.when(repository.findById(accountId)).thenReturn(Optional.of(account));

        // when
        var result = SUT.getById(accountId);

        // then
        assertThat(result, is(equalTo(account.withContacts(contacts))));
    }

    @Test
    void whenIdMismatchOnUpdate_ShouldThrowAccountExceptionActionNotAllowed() {
        // given
        var argumentId = UUID.randomUUID();
        var accountId = UUID.randomUUID();
        var account = Account.builder()
                .id(accountId)
                .build();

        // when, then
        assertThatThrownBy(() -> SUT.update(argumentId, account))
                .isInstanceOf(AccountException.ActionNotAllowedException.class)
                .hasMessage("Action on Account not allowed :: id mismatch");
    }
}