package org.example.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
@Provider
public class InternalServerErrorExceptionMapper implements ExceptionMapper<InternalServerErrorException> {
    @Override
    public Response toResponse(InternalServerErrorException exception) {
        //TODO -> check on it
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception.getMessage()+"test test test")
                .build();
    }
}
