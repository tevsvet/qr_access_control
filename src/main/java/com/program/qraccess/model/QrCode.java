package com.program.qraccess.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "qr_codes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QrCode {

    @Id
    @Column(name = "member_id")
    private Long memberId;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Builder
    private QrCode(Member member, UUID uuid) {
        if (member == null) {
            throw new IllegalArgumentException("Member can not be null");
        }
        validateUuid(uuid);

        this.memberId = member.getId();
        this.uuid = uuid;
    }

    public void changeUuid(UUID uuid) {
        validateUuid(uuid);
        this.uuid = uuid;
    }

    private void validateUuid(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID can not be null");
        }
    }
}
