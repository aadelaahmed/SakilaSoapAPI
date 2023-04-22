package org.example.exception;

import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PersistenceMapper implements ExceptionMapper<PersistenceException> {
    /*
     thrown for various JPA-related errors, such as constraint violations or transaction rollbacks
     */

    @Override
    public Response toResponse(PersistenceException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    }
}
