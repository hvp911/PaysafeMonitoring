package net.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import net.rest.response.ResponseStatus;
import net.rest.response.ResponseWriter;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<APIException> {
	public Response toResponse(APIException ex) {
		return ResponseWriter.write("Some iNternal error", ResponseStatus.INTERNAL_ERROR);
	}
}
