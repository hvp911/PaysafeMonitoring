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
		if (!serverStatusModel.validateHostName(monitoringBody.hostName)) {
			throw new InvalidRequestException("No moniotring details found for requested host.");
		}
		return ResponseWriter.ok(serverStatusModel.getServerStatusList(monitoringBody.hostName));
	}

	private static class ServerMonitoringBody {

		private String hostName;

		@SuppressWarnings("unused")
		public String getHostName() {
			return hostName;
		}

		@SuppressWarnings("unused")
		public void setHostName(String hostName) {
			this.hostName = hostName;
		}

		@Override
		public String toString() {
			return "Server Overview Body";
		}

	}

	ServerStatusModel serverStatusModel;

}
