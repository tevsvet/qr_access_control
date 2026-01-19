package com.program.qraccess.dto;

import java.util.Optional;

public record UpdateMemberRequest(
        Optional<String> firstName,
        Optional<String> lastName,
        Optional<String> middleName
) {}
