package com.program.qraccess.service;

import com.program.qraccess.dto.ScanQrRequest;
import com.program.qraccess.dto.ScanQrResponse;
import com.program.qraccess.exception.MemberNotFoundException;
import com.program.qraccess.model.Member;
import com.program.qraccess.repository.MemberRepository;
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

    @Transactional
    public ScanQrResponse scanQr(ScanQrRequest request) {
        UUID qr = request.qrUuid();
        log.info("QR scan request: {}", qr);

        Member member = memberRepository.findByQrUuid(qr)
                .orElseThrow(() -> new MemberNotFoundException("QR code not found"));

        UUID newQr = UUID.randomUUID();
        member.setQrUuid(newQr);

        log.info("Access granted for member with id = {}. QR rotated", member.getId());

        return new ScanQrResponse(member.getFullName());
    }
}
