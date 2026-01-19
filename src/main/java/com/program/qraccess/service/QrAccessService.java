package com.program.qraccess.service;

import com.program.qraccess.dto.ScanQrRequest;
import com.program.qraccess.dto.ScanQrResponse;
import com.program.qraccess.exception.MemberNotFoundException;
import com.program.qraccess.exception.QrCodeNotFoundException;
import com.program.qraccess.mapper.QrMapper;
import com.program.qraccess.model.Member;
import com.program.qraccess.model.QrCode;
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

    private final MemberRepository memberRepository;
    private final QrCodeRepository qrCodeRepository;
    private final QrMapper qrMapper;

    @Transactional
    public ScanQrResponse scanQr(ScanQrRequest request) {
        UUID uuid = request.uuid();
        log.info("QR scan request: {}", uuid);

        QrCode qrCode = qrCodeRepository.findByUuid(uuid)
                .orElseThrow(() -> new QrCodeNotFoundException("QR code not found"));

        Member member = memberRepository.findById(qrCode.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        qrCode.changeUuid(UUID.randomUUID());

        log.info("Access granted for member with id = {}. QR rotated", member.getId());

        return qrMapper.toResponse(member);
    }
}
