package com.github.tmquotebot.searchspider.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WebException extends RuntimeException {

    protected HttpStatus status;
    private final Object[] args;

    public WebException(String message, Object... args) {
        super(message);
        this.args = args;
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public WebException(String message, Throwable cause, Object... args) {
        super(message, cause);
        this.args = args;
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public WebException(String message, HttpStatus status, Object... args) {
        super(message);
        this.args = args;
        this.status = status;
    }

    public WebException(String message, HttpStatus status, Throwable cause, Object... args) {
        super(message, cause);
        this.args = args;
        this.status = status;
    }
}
