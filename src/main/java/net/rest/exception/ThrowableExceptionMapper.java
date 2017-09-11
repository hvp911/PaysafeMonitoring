package net.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import net.rest.response.ResponseStatus;
import net.rest.response.ResponseWriter;

/**
 * Catch all exception thrown. Reformat
 */
@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        Response response;
        if (exception instanceof WebApplicationException) {
            WebApplicationException webEx = (WebApplicationException) exception;
            ResponseStatus status = ResponseStatus.fromHttpStatusCode(webEx.getResponse().getStatus());
            response = ResponseWriter.write(null, status);
        } else {
            response = ResponseWriter.write(null, ResponseStatus.INTERNAL_ERROR);
        }
        return response;
    }
}
