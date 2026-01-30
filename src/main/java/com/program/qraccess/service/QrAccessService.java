package com.program.qraccess.service;

import com.program.qraccess.dto.ScanQrRequest;
import com.program.qraccess.dto.ScanQrResponse;
import com.program.qraccess.exception.MemberNotFoundException;
import com.program.qraccess.exception.QrCodeNotFoundException;
import com.program.qraccess.mapper.QrMapper;
import com.program.qraccess.model.MemberEntity;
import com.program.qraccess.model.QrCodeEntity;
import com.program.qraccess.repository.MemberRepository;
import com.program.qraccess.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class QrAccessService {

    private final QrCodeRepository qrCodeRepository;
    private final QrMapper qrMapper;

    @Transactional
    public ScanQrResponse scanQr(ScanQrRequest request) {
        UUID uuid = request.uuid();
        log.info("QR scan request: {}", uuid);

        QrCodeEntity qrCode = qrCodeRepository.findByUuid(uuid)
                .orElseThrow(() -> new QrCodeNotFoundException("QR code not found"));

        MemberEntity member = qrCode.getMember();

        qrCode.setUuid(UUID.randomUUID());

        log.info("Access granted for member with id = {}. QR rotated", member.getId());

        return qrMapper.toResponse(member);
    }
}
