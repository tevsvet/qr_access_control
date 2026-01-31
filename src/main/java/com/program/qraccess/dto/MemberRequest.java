package com.program.qraccess.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberRequest(
        @NotBlank(message = "First name must not be blank")
        String firstName,

        @NotBlank(message = "Last name must not be blank")
        String lastName,

        String middleName
) {}
