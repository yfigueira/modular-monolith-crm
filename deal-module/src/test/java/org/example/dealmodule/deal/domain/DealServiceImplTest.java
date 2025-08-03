package org.example.dealmodule.deal.domain;

import org.example.dealmodule.exception.DealException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class DealServiceImplTest {

    @Mock
    DealRepository repository;

    @InjectMocks
    DealServiceImpl SUT;

    @Test
    void whenNotFound_ShouldThrowDealExceptionNotFound() {
        // given
        var unknownId = UUID.randomUUID();
        Mockito.when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> SUT.getById(unknownId))
                .isInstanceOf(DealException.ResourceNotFoundException.class)
                .hasMessage("Deal not found :: %s".formatted(unknownId));
    }
}