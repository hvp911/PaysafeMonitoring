package net.rest.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.rest.constants.ApplicationConstants;

public class HTTPUtility {

	public boolean reachabilityCheck(String hostName) {
		String url = ApplicationConstants.HTTP_PREFIX + hostName + ApplicationConstants.MONITORING_ENDPOINT;
		return (getServerStatus(url) != null);
	}

	public String getServerStatus(String url) {
		HttpGet request = new HttpGet(url);
		String responseBody;
		HttpClient client = HttpClientBuilder.create().build();
		try {
			HttpResponse response = client.execute(request);
			responseBody = EntityUtils.toString(response.getEntity());
			JsonObject jsonResponse = new JsonParser().parse(responseBody).getAsJsonObject();
			if (response.getStatusLine().getStatusCode() > 399) {
				return null;
			}
			return jsonResponse.get(ApplicationConstants.SERVER_STATUS_RESPONSE_FIELD).getAsString();
		} catch (Exception e) {
			return null;
		}
	}
}
