package com.jtm.notesapp.commons;

public class LoginExistsException extends Exception {
    public LoginExistsException(String message) {
        super(message);
    }
}
