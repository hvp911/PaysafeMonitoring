package net.rest.response;

import javax.ws.rs.core.Response;

/**
 * Base interface for statuses used in responses.
 */
public interface ResponseStatusType {

	/**
	 * Get the associated http status code.
	 */
	public Response.Status getHttpStatusCode();

	/**
	 * Get the associated message.
	 */
	public String getMessage();
}