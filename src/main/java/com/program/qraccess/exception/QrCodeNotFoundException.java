package com.program.qraccess.exception;

public class QrCodeNotFoundException extends RuntimeException {

    public QrCodeNotFoundException(String message) {
        super(message);
    }
}
