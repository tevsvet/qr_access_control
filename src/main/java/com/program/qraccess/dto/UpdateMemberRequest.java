package com.program.qraccess.dto;

import com.program.qraccess.validation.NotBlankIfPresent;

public record UpdateMemberRequest(

        @NotBlankIfPresent(message = "First name cannot be blank if provided")
        String firstName,

        @NotBlankIfPresent(message = "Last name cannot be blank if provided")
        String lastName,

        String middleName
) {}
