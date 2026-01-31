package com.program.qraccess.mapper;

import com.program.qraccess.dto.ScanQrResponse;
import com.program.qraccess.model.MemberEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QrMapper {

    ScanQrResponse toResponse(MemberEntity member);
}
