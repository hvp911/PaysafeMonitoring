package net.rest.scheduler;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.rest.constants.ApplicationConstants;
import net.rest.model.ServerStatusModel;
import net.rest.util.HTTPUtility;

public class SchedulersHandler {

	private SchedulersHandler() {
	}

	public static SchedulersHandler getSchedulerHandler() {
		if (instance == null) {
			instance = new SchedulersHandler();
		}
		return instance;
	}

	public boolean validateSchedulersAvailability(String hostName) {
		return allSchedulers.containsKey(hostName);
	}

	public void createNewScheduler(String hostName, int interval) {
		if (!allSchedulers.containsKey(hostName)) {
			MonitoringScheduler scheduler = new MonitoringScheduler(hostName, interval);
			allSchedulers.put(hostName, scheduler);
		} else {
			// Do not schedule another one or raise exception.
		}
	}

	public void removeScheduler(String hostName) {
		if (allSchedulers.containsKey(hostName)) {
			MonitoringScheduler scheduler = getScheduler(hostName);
			scheduler.shutdownScheduler();
			allSchedulers.remove(hostName);
		} else {
			// Do nothing or raise exception
		}
	}

	public MonitoringScheduler getScheduler(String hostName) {
		return allSchedulers.get(hostName);
	}

	private static HashMap<String, MonitoringScheduler> allSchedulers = new HashMap<String, MonitoringScheduler>();
	private static SchedulersHandler instance;

	private class MonitoringScheduler {

		public MonitoringScheduler(String hostName, int interval) {
			httpUtility = new HTTPUtility();
			this.hostName = hostName;
			serverStatusModel = ServerStatusModel.getServerStatusModel();
			monitoringScheduler.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					String url = ApplicationConstants.HTTP_PREFIX + hostName + ApplicationConstants.MONITORING_ENDPOINT;
					String serverStatus = httpUtility.getServerStatus(url);
					serverStatusModel.updateServerStatusModel(hostName, serverStatus);
				}
			}, 0, interval, TimeUnit.SECONDS);
		}

		private final ScheduledExecutorService monitoringScheduler = Executors.newSingleThreadScheduledExecutor();
		private ServerStatusModel serverStatusModel;
		private String hostName;
		private HTTPUtility httpUtility;

		public void shutdownScheduler() {
			serverStatusModel.removeServerStatusModel(this.hostName);
			monitoringScheduler.shutdown();
		}
	}
}
