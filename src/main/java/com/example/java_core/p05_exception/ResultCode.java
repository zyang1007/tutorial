package com.example.java_core.p05_exception;

public enum ResultCode implements ErrorCode{

    // enum codes and message
    SUCCESS(200, "operation succeeded!"),
    FAILED(500, "operation failed!"),
    VALIDATE_FAILED(404, "validation failed!"),
    UNAUTHORIZED(401, "Not logged in or token has expired!"),
    FORBIDDEN(403, "Permission denied!");

    private long code;
    private String message;

    private ResultCode(long code, String message) {  // private constructor
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
