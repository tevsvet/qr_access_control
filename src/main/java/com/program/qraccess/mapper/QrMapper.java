package com.program.qraccess.mapper;

import com.program.qraccess.dto.ScanQrRequest;
import com.program.qraccess.dto.ScanQrResponse;
import com.program.qraccess.model.Member;
import com.program.qraccess.model.QrCode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QrMapper {

    ScanQrResponse toResponse(Member member);
}
