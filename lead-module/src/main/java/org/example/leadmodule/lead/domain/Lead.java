package org.example.leadmodule.lead.domain;

import lombok.Builder;
import lombok.With;
import org.example.activitymodule.ActivityInternalDto;
import org.example.leadmodule.company.domain.Company;
import org.example.leadmodule.jobtitle.domain.JobTitle;
import org.example.usermodule.UserInternalDto;

import java.util.List;
import java.util.UUID;

@Builder
public record Lead(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String subject,
        String city,
        @With LeadState state,
        @With Boolean isActive,
        @With UserInternalDto owner,
        @With JobTitle jobTitle,
        @With Company company,
        @With List<ActivityInternalDto> activities
) {
}
