package br.com.bianeck.supportportal.exception.domain;

public class EmailExistException extends Exception{

    public EmailExistException(String message) {
        super(message);
    }
}
