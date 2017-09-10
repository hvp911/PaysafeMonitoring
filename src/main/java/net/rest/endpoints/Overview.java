package net.rest.endpoints;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import net.rest.exception.APIException;
import net.rest.exception.InvalidRequestException;
import net.rest.model.ServerStatusModel;
import net.rest.response.ResponseWriter;

@Path("")
public class Overview {

	public Overview() {
		serverStatusModel = ServerStatusModel.getServerStatusModel();
	}

	@POST
	@Path("/overview")
	public Response startUp(ServerMonitoringBody monitoringBody) throws APIException {
		// TODO: As part of quick solution, throwing exception like following.
		if (monitoringBody == null) {
			throw new InvalidRequestException("Invalid Request body.");
		}
		if (!serverStatusModel.validateHostName(monitoringBody.hostname)) {
			throw new InvalidRequestException("No moniotring details found for requested host.");
		}
		return ResponseWriter.ok(serverStatusModel.getServerStatusList(monitoringBody.hostname));
	}

	private static class ServerMonitoringBody {
		@SuppressWarnings("unused")
		public String getHostname() {
			return hostname;
		}

		@SuppressWarnings("unused")
		public void setHostname(String hostname) {
			this.hostname = hostname;
		}

		@Override
		public String toString() {
			return "Server Overview Body";
		}

		private String hostname;
	}

	ServerStatusModel serverStatusModel;

}
