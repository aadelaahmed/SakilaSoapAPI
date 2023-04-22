package org.example.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityAlreadyExistExceptionMapper implements ExceptionMapper<EntityAlreadyExistException> {
    @Override
    public Response toResponse(EntityAlreadyExistException exception) {
        System.out.println("the EntityAlreadyExistException mapper is executed");
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
    }
}
