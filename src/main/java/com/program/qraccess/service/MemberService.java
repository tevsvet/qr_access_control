package com.program.qraccess.service;

import com.program.qraccess.dto.MemberRequest;
import com.program.qraccess.dto.MemberResponse;
import com.program.qraccess.dto.UpdateMemberRequest;
import com.program.qraccess.mapper.MemberMapper;
import com.program.qraccess.model.MemberEntity;
import com.program.qraccess.exception.MemberNotFoundException;
import com.program.qraccess.model.QrCodeEntity;
import com.program.qraccess.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final QrCodeFactory qrCodeFactory;
    private final MemberMapper memberMapper;

    @Transactional(readOnly = true)
    public List<MemberResponse> getAll() {
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::toResponse)
                .toList();
    }

    @Transactional
    public MemberResponse create(MemberRequest request) {
        log.info("Creating new member");

        MemberEntity member = memberMapper.toEntity(request);

        String middleName = request.middleName();
        member.setMiddleName(middleName.isBlank() ? null : middleName );

        QrCodeEntity qrCode = qrCodeFactory.createQrFor(member);
        member.setQrCode(qrCode);

        memberRepository.save(member);

        log.info("Member created with id = {}", member.getId());

        return memberMapper.toResponse(member);
    }

    @Transactional
    public MemberResponse update(Long id, UpdateMemberRequest request) {
        log.info("Updating full name of member with id = {}", id);

        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        memberMapper.updateMember(member, request);

        log.info("Member full name updated");

        return memberMapper.toResponse(member);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting member with id = {}", id);

        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        memberRepository.delete(member);

        log.info("Member with id = {} deleted", id);
    }
}