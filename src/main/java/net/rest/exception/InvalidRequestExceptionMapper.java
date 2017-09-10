package net.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import net.rest.response.ResponseStatus;
import net.rest.response.ResponseWriter;

@Provider
public class InvalidRequestExceptionMapper implements ExceptionMapper<InvalidRequestException> {
	public Response toResponse(InvalidRequestException ex) {
		return ResponseWriter.write(ex.getMessage(), ResponseStatus.BAD_REQUEST);
	}
}
