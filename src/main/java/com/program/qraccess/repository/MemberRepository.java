package com.program.qraccess.repository;

import com.program.qraccess.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByQrUuid(UUID qrUuid);
}
