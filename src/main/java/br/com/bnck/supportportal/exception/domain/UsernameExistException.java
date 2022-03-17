package br.com.bnck.supportportal.exception.domain;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: supportportal
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 22/02/2022
 * Modificado: 16/03/2022
 * Hora: 23:41
 */
public class UsernameExistException extends Exception{

    public UsernameExistException(String message) {
        super(message);
    }
}
