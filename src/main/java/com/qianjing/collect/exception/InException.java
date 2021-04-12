package com.qianjing.collect.exception;

public class InException extends RuntimeException {
    public InException(String msg) {
        super(msg);
    }

    public InException(Throwable e) {
        super(e);
    }
}
