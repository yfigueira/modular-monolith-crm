package org.example.accountmodule;

import java.util.UUID;

public interface ContactInternalApi {

    ContactInternalDto getInternalById(UUID id);
}
