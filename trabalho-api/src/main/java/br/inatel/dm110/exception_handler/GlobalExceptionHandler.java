package br.inatel.dm110.exception_handler;

import br.inatel.dm110.exceptions.ProductNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<ProductNotFoundException> {

    @Override
    public Response toResponse(ProductNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(exception.getMessage()))
                .build();
    }
}
