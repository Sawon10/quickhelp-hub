package com.oauth.quickhelp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDraft {

    @NotNull(message = "Role is required.")
    private RegistrationRole role;

    @NotBlank(message = "Full name is required.")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters.")
    private String fullName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email address.")
    @Size(max = 150, message = "Email must not exceed 150 characters.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Size(max = 20, message = "Phone number must not exceed 20 characters.")
    private String phoneNumber;

    @NotBlank(message = "City or area is required.")
    @Size(max = 120, message = "City or area must not exceed 120 characters.")
    private String cityArea;

    @Size(max = 100, message = "Household type must not exceed 100 characters.")
    private String householdType;

    @Size(max = 100, message = "Service need must not exceed 100 characters.")
    private String serviceNeed;

    @Size(max = 100, message = "Budget range must not exceed 100 characters.")
    private String budgetRange;

    @Size(max = 100, message = "Primary skill must not exceed 100 characters.")
    private String primarySkill;

    @Size(max = 100, message = "Experience level must not exceed 100 characters.")
    private String experienceLevel;

    @Size(max = 120, message = "Availability must not exceed 120 characters.")
    private String availability;

    @Size(max = 100, message = "Work style must not exceed 100 characters.")
    private String workStyle;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters.")
    private String notes;

    public enum RegistrationRole {
        RECRUITER,
        HELPER
    }
}


