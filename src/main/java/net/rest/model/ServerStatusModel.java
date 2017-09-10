package net.rest.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerStatusModel {
	private ServerStatusModel() {
	}

	public static ServerStatusModel getServerStatusModel() {
		if (instance == null) {
			instance = new ServerStatusModel();
		}
		return instance;
	}

	public void removeServerStatusModel(String hostName) {
		if (serverStatusModelMap.containsKey(hostName)) {
			serverStatusModelMap.remove(hostName);
		} else {
			// Raise exception
		}
	}

	public void updateServerStatusModel(String hostName, String serverStatus) {
		if (checkUpdateRequired(hostName, serverStatus)) {
			String dateTimeString = System.currentTimeMillis() + "";
			ArrayList<StatusModel> statusModelList = new ArrayList<>();
			synchronized (hostName) {
				if (serverStatusModelMap.containsKey(hostName)) {
					statusModelList = serverStatusModelMap.get(hostName);
				} else {
					serverStatusModelMap.put(hostName, statusModelList);
				}
				statusModelList.add(new StatusModel(dateTimeString, serverStatus));
			}
		}
	}

	private static boolean checkUpdateRequired(String hostName, String serverStatus) {
		String previousServerStatus = null;
		if (previousStatus.containsKey(hostName)) {
			previousServerStatus = previousStatus.get(hostName);
		}
		if (previousServerStatus == null || previousServerStatus != serverStatus) {
			synchronized (hostName) {
				previousStatus.put(hostName, serverStatus);
			}
			return true;
		}
		return false;
	}

	public ArrayList<StatusModel> getServerStatusList(String hostName) {
		if (serverStatusModelMap.containsKey(hostName)) {
			return serverStatusModelMap.get(hostName);
		}
		return null;
	}

	public boolean validateHostName(String hostName) {
		return serverStatusModelMap.containsKey(hostName);
	}

	public static HashMap<String, String> previousStatus = new HashMap<String, String>();
	public static HashMap<String, ArrayList<StatusModel>> serverStatusModelMap = new HashMap<String, ArrayList<StatusModel>>();

	@Override
	public String toString() {
		return super.toString();
	}

	private static ServerStatusModel instance;

	private static class StatusModel {
		public StatusModel(String dateTimeString, String serverStatus) {
			this.setDateTimeString(dateTimeString);
			this.setServerStatus(serverStatus);
		}

		private String dateTimeString;
		private String serverStatus;

		@Override
		public String toString() {
			return super.toString();
		}

		@SuppressWarnings("unused")
		public String getDateTimeString() {
			return dateTimeString;
		}

		public void setDateTimeString(String dateTimeString) {
			this.dateTimeString = dateTimeString;
		}

		@SuppressWarnings("unused")
		public String getServerStatus() {
			return serverStatus;
		}

		public void setServerStatus(String serverStatus) {
			this.serverStatus = serverStatus;
		}
	}
}
