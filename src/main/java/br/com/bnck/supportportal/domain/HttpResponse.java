package br.com.bnck.supportportal.domain;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: supportportal
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 30/01/2022
 * Modificado: 14/03/2022
 * Hora: 01:27
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HttpResponse {
    private Date timeStamp;
    private int httpStatusCode; // 200, 201, 400, 500
    private HttpStatus httpStatus;
    private String reason;
    private String message;

    public HttpResponse(int value, HttpStatus httpStatus, String toUpperCase, String message) {

    }
}
