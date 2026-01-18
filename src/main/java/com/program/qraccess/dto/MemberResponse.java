package com.program.qraccess.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MemberResponse {

    private Long id;
    private String fullName;
    private UUID qrUuid;
}
