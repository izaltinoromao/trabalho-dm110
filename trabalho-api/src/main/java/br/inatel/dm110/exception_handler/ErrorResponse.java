package br.inatel.dm110.exception_handler;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

}
