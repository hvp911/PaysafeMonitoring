package net.rest.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import net.rest.exception.APIException;
import net.rest.response.ResponseWriter;

@Path("")
public class HelloWorldHandler {

	@GET
	@Path("/helloworld")
	public Response startUp() throws APIException {
		return ResponseWriter.ok("helloworld");
	}
}
