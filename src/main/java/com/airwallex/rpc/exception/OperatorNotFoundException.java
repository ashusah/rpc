package com.airwallex.rpc.exception;

public class OperatorNotFoundException extends RuntimeException {
    public OperatorNotFoundException(String message, Exception e) { super(message, e);}

    public OperatorNotFoundException(String message) { super(message); }
}
