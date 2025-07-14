package org.example.leadmodule.company.domain;

import org.example.leadmodule.exception.LeadException;
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
class CompanyServiceImplTest {

    @Mock
    CompanyRepository repository;

    @InjectMocks
    CompanyServiceImpl SUT;

    @Test
    void whenAlreadyExists_ShouldThrowLeadServiceExceptionAlreadyExists() {
        // given
        var newCompany = Company.builder()
                .id(UUID.randomUUID())
                .name("Existing company")
                .build();

        Mockito.when(repository.existsByName(newCompany.name())).thenReturn(true);

        // when, then
        assertThatThrownBy(() -> SUT.create(newCompany))
                .isInstanceOf(LeadException.ResourceAlreadyExistsException.class)
                .hasMessage("Company already exists :: %s".formatted(newCompany.name()));
    }

    @Test
    void whenNotAlreadyExists_ShouldReturnCreatedCompany() {
        //given
        var newCompany = Company.builder()
                .name("Test company")
                .build();

        var createdCompany = Company.builder()
                .id(UUID.randomUUID())
                .name("Test company")
                .build();

        Mockito.when(repository.create(newCompany)).thenReturn(createdCompany);

        // when
        var result = SUT.create(newCompany);

        // then
        assertThat(result, is(equalTo(createdCompany)));
    }

    @Test
    void whenNotFound_ShouldThrowAccountServiceExceptionNotFound() {
        // given
        var unknownId = UUID.randomUUID();
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(LeadException.ResourceNotFoundException.class)
                .hasMessage("Company not found :: %s".formatted(unknownId));
    }
}