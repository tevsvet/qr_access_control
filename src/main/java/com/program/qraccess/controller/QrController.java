package com.program.qraccess.controller;

import com.program.qraccess.dto.ScanQrRequest;
import com.program.qraccess.dto.ScanQrResponse;
import com.program.qraccess.service.QrAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qr")
@RequiredArgsConstructor
public class QrController {

    private final QrAccessService qrAccessService;

    @PostMapping("/scan")
    public ScanQrResponse scan(@RequestBody ScanQrRequest request) {
        return qrAccessService.scanQr(request);
    }
}
