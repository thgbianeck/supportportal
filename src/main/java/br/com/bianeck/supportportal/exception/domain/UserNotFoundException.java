package br.com.bianeck.supportportal.exception.domain;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message) {
        super(message);
    }
}
