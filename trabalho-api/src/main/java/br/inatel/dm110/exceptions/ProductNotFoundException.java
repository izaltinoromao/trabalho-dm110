package br.inatel.dm110.exceptions;

import jakarta.ws.rs.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
