package org.example.exception;
import jakarta.persistence.RollbackException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
@Provider
public class RollbackExceptionMapper implements ExceptionMapper<RollbackException> {
    @Override
    public Response toResponse(RollbackException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Transaction failed: " + e.getMessage())
                .build();
    }
}
