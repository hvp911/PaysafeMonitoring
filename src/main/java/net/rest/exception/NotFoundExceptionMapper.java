package net.rest.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import net.rest.response.ResponseStatus;
import net.rest.response.ResponseWriter;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
	public Response toResponse(NotFoundException ex) {
		return ResponseWriter.write("Not Found", ResponseStatus.NOT_FOUND);
	}
}
