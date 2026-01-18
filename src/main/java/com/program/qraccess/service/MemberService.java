package com.program.qraccess.service;

import com.program.qraccess.dto.MemberRequest;
import com.program.qraccess.dto.MemberResponse;
import com.program.qraccess.model.Member;
import com.program.qraccess.exception.MemberNotFoundException;
import com.program.qraccess.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member processQr(UUID qrUuid) {
        log.info("Processing QR scan: {}", qrUuid);

        Member member = memberRepository.findByQrUuid(qrUuid)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        UUID newQr = UUID.randomUUID();
        member.setQrUuid(newQr);

        log.info("QR for member {} updated to {}", member.getId(), newQr);

        return member;
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> getAll() {
        return memberRepository.findAll()
                .stream()
                .map(this::toResponce)
                .collect(Collectors.toList());
    }

    @Transactional
    public MemberResponse create(MemberRequest request) {
        log.info("Creating member with fullName = {}", request.getFullName());

        Member member = Member.builder()
                .fullName(request.getFullName())
                .qrUuid(UUID.randomUUID())
                .build();

        memberRepository.save(member);

        log.info("Member created with id = {}", member.getId());

        return toResponce(member);
    }

    @Transactional
    public MemberResponse update(Long id, MemberRequest request) {
        log.info("Updating member with id = {}", id);

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        member.setFullName(request.getFullName());

        log.info("Member with id = {} updated", id);

        return toResponce(member);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting member with id = {}", id);

        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException("Member not found");
        }
        memberRepository.deleteById(id);

        log.info("Member with id = {} deleted", id);
    }

    private MemberResponse toResponce(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .fullName(member.getFullName())
                .qrUuid(member.getQrUuid())
                .build();
    }
}