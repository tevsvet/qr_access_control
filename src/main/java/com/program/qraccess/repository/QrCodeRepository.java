package com.program.qraccess.repository;

import com.program.qraccess.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QrCodeRepository extends JpaRepository<QrCode, Long> {
    Optional<QrCode> findByUuid(UUID uuid);
    Optional<QrCode> findByMemberId(Long memberId);
    void deleteByMemberId(Long memberId);
}
