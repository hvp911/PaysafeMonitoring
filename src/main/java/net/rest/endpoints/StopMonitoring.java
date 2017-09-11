package net.rest.endpoints;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import net.rest.exception.APIException;
import net.rest.exception.InvalidRequestException;
import net.rest.response.ResponseWriter;
import net.rest.scheduler.SchedulersHandler;
import net.rest.util.HTTPUtility;

@Path("")
public class StopMonitoring {
	public StopMonitoring() {
		schedulerHandler = SchedulersHandler.getSchedulerHandler();
		httpUtility = new HTTPUtility();
	}

	@POST
	@Path("/rest/stop-monitoring")
	public Response stopServerMonitoring(ServerMonitoringBody monitoringBody) throws APIException {
		if (monitoringBody == null) {
			throw new InvalidRequestException("Invalid Request body.");
		}
		if (monitoringBody.hostname == null) {
			throw new InvalidRequestException("Required body filed missing : 'hostname'");
		}

		if (!httpUtility.reachabilityCheck(monitoringBody.hostname)) {
			throw new InvalidRequestException("Invalid host name.");
		}
		if (!schedulerHandler.validateSchedulersAvailability(monitoringBody.hostname)) {
			throw new InvalidRequestException("Requested system monitoring has already been disabled.");
		}
		schedulerHandler.removeScheduler(monitoringBody.hostname);

		return ResponseWriter.ok("Monitoring Stopped");
	}

	public static class ServerMonitoringBody {
		public String getHostname() {
			return hostname;
		}

		public void setHostname(String hostname) {
			this.hostname = hostname;
		}

		@Override
		public String toString() {
			return super.toString();
		}

		private String hostname;
	}

	private SchedulersHandler schedulerHandler;
	private HTTPUtility httpUtility;
}
