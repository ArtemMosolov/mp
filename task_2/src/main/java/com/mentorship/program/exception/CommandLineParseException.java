package com.mentorship.program.exception;

public class CommandLineParseException extends RuntimeException {

    public CommandLineParseException(String message) {
        super(message);
    }

    public CommandLineParseException(Throwable cause) {
        super(cause);
    }
}