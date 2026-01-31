package com.program.qraccess.service;

import com.program.qraccess.model.MemberEntity;
import com.program.qraccess.model.QrCodeEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QrCodeFactory {
    public QrCodeEntity createQrFor(MemberEntity member) {
        return QrCodeEntity.builder()
                .member(member)
                .uuid(UUID.randomUUID())
                .build();
    }
}
