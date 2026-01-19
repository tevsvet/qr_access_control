package com.program.qraccess.dto;

public record MemberResponse(
        Long id,
        String firstName,
        String lastName,
        String middleName
) {}
