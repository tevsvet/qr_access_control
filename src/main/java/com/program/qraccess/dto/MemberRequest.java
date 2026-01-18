package com.program.qraccess.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRequest {

    @NotBlank(message = "Full name must not be blank")
    private String fullName;
}
