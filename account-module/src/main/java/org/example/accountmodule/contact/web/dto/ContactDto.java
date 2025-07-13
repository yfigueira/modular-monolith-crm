package org.example.accountmodule.contact.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.example.accountmodule.Contact;
import org.example.accountmodule.account.domain.Account;
import org.example.accountmodule.common.web.DtoMapper;
import org.example.accountmodule.contact.domain.ContactPriority;
import org.example.accountmodule.jobtitle.web.JobTitleDto;
import org.example.activitymodule.activity.web.dto.ActivityDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Builder
public record ContactDto(
        UUID id,
        @NotBlank(message = "First name required")
        String firstName,
        @NotBlank(message = "Last name required")
        String lastName,
        @NotBlank(message = "Email required")
        @Email(message = "Email format not valid")
        String email,
        Integer priority,
        UUID company,
        JobTitleDto jobTitle,
        String phoneNumber,
        String privateEmail,
        String privatePhoneNumber,
        List<ActivityDto> activities
) {
    @Mapper
    public interface ContactDtoMapper extends DtoMapper<Contact, ContactDto> {

        default Account mapAccount(UUID entity) {
            return Account.builder().id(entity).build();
        }

        default UUID mapAccount(Account domain) {
            return domain.id();
        }

        default ContactPriority mapContactPriority(Integer dto) {
            return Arrays.stream(ContactPriority.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(ContactPriority.LOW);
        }

        default Integer mapContactPriority(ContactPriority domain) {
            return domain.getCode();
        }
    }

    public static ContactDtoMapper mapper() {
        return Mappers.getMapper(ContactDtoMapper.class);
    }
}
