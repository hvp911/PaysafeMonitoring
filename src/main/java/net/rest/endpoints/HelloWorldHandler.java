package net.rest.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.rest.exception.APIException;
import net.rest.response.ResponseWriter;

@Path("")
public class HelloWorldHandler {

	@GET
	@Path("/helloworld")
	@Produces(MediaType.APPLICATION_JSON)
	public Response startUp() throws APIException {
		return ResponseWriter.ok("helloworld");
	}

}
