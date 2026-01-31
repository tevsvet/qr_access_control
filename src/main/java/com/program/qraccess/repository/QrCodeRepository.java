package com.program.qraccess.repository;

import com.program.qraccess.model.QrCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QrCodeRepository extends JpaRepository<QrCodeEntity, Long> {
    Optional<QrCodeEntity> findByUuid(UUID uuid);
}
