package com.program.qraccess.controller;

import com.program.qraccess.dto.MemberRequest;
import com.program.qraccess.dto.MemberResponse;
import com.program.qraccess.dto.UpdateMemberRequest;
import com.program.qraccess.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberResponse> getAll() {
        return memberService.getAll();
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(@Valid @RequestBody MemberRequest request) {
        return ResponseEntity.ok(memberService.create(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateMemberRequest request) {
        return ResponseEntity.ok(memberService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
