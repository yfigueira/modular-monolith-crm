package org.example.accountmodule.contact.domain;

import lombok.Builder;
import lombok.With;
import org.example.accountmodule.account.domain.Account;
import org.example.accountmodule.jobtitle.domain.JobTitle;
import org.example.activitymodule.ActivityInternalDto;

import java.util.List;
import java.util.UUID;

@Builder
public record Contact(
        UUID id,
        String firstName,
        String lastName,
        String email,
        ContactPriority priority,
        @With Account company,
        @With JobTitle jobTitle,
        String phoneNumber,
        String privateEmail,
        String privatePhoneNumber,
        @With List<ActivityInternalDto> activities
) {
}
