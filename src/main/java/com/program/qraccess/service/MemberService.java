package com.program.qraccess.service;

import com.program.qraccess.dto.MemberRequest;
import com.program.qraccess.dto.MemberResponse;
import com.program.qraccess.dto.UpdateMemberRequest;
import com.program.qraccess.mapper.MemberMapper;
import com.program.qraccess.model.Member;
import com.program.qraccess.exception.MemberNotFoundException;
import com.program.qraccess.model.QrCode;
import com.program.qraccess.repository.MemberRepository;
import com.program.qraccess.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final QrCodeRepository qrCodeRepository;
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

        Member member = memberMapper.toEntity(request);
        memberRepository.save(member);

        QrCode qrCode = QrCode.builder()
                .member(member)
                .uuid(UUID.randomUUID())
                .build();
        qrCodeRepository.save(qrCode);

        log.info("Member created with id = {}", member.getId());

        return memberMapper.toResponse(member);
    }

    @Transactional
    public MemberResponse update(Long id, UpdateMemberRequest request) {
        log.info("Updating full name of member with id = {}", id);

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        request.firstName().ifPresent(member::changeFirstName);
        request.lastName().ifPresent(member::changeLastName);
        request.middleName().ifPresent(name -> member.changeMiddleName(name.isBlank() ? null : name));

        log.info("Member full name updated");

        return memberMapper.toResponse(member);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting member with id = {}", id);

        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException("Member not found");
        }

        qrCodeRepository.deleteByMemberId(id);
        memberRepository.deleteById(id);

        log.info("Member with id = {} deleted", id);
    }
}