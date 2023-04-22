package org.example.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundMapper implements ExceptionMapper<EntityNotFoundException> {
    /*
    thrown when an entity with the specified ID is not found in the database
     */
    @Override
    public Response toResponse(EntityNotFoundException e) {
        System.out.println("Execute mapper for entity not found exception");
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
