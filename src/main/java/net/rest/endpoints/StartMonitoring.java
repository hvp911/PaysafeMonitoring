package net.rest.endpoints;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import net.rest.exception.APIException;
import net.rest.exception.InvalidRequestException;
import net.rest.response.ResponseWriter;
import net.rest.scheduler.SchedulersHandler;
import net.rest.util.HTTPUtility;

//TODO: Need to add checks on Content-Type headers.
// Invalid content-type header turns into 500.

@Path("")
public class StartMonitoring {
	public StartMonitoring() {
		schedulerHandler = SchedulersHandler.getSchedulerHandler();
		httpUtility = new HTTPUtility();
	}

	@POST
	@Path("/start-monitoring")
	public Response startServerMonitoring(ServerMonitoringBody monitoringBody) throws APIException {
		if (monitoringBody == null) {
			throw new InvalidRequestException("Invalid Request body.");
		}
		if (monitoringBody.hostname == null) {
			throw new InvalidRequestException("Required body filed missing : 'hostname'");
		}
		if (monitoringBody.interval == 0) {
			throw new InvalidRequestException("Invalid body field 'interval', should be > 0.");
		}

		if (!httpUtility.reachabilityCheck(monitoringBody.hostname)) {
			throw new InvalidRequestException("Invalid host name.");
		}
		if (schedulerHandler.validateSchedulersAvailability(monitoringBody.hostname)) {
			throw new InvalidRequestException("Monitoring of requested system is already scheduled.");
		}
		schedulerHandler.createNewScheduler(monitoringBody.hostname, monitoringBody.interval);
		return ResponseWriter.ok("Monitoring Started");
	}

	public static class ServerMonitoringBody {
		public void setInterval(int interval) {
			this.interval = interval;
		}

		public int getInterval() {
			return interval;
		}

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
		private int interval;
	}

	private SchedulersHandler schedulerHandler;
	private HTTPUtility httpUtility;
}
