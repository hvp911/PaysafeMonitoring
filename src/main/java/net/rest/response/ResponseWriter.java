package net.rest.response;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ResponseWriter {

	static class SerializableEntity {
		// TODO: remove null attributes returned from response.
		protected SerializableEntity(ResponseWriter writer) {
			this.message = writer.message != null && !writer.message.isEmpty() ? writer.message
					: writer.status.getMessage();

			if (this.message == null || this.message.isEmpty()) {
				Response.Status httpStatus = writer.status.getHttpStatusCode();
				if (httpStatus.getFamily() == Response.Status.Family.CLIENT_ERROR
						|| httpStatus.getFamily() == Response.Status.Family.SERVER_ERROR) {
					this.message = httpStatus.getReasonPhrase();
				}
			}

			this.response = writer.response;
		}

		public String getMessage() {
			return message;
		}

		public Object getResponse() {
			return response;
		}

		private String message;
		private Object response;
	}

	protected ResponseWriter() {
	}

	protected ResponseWriter(ResponseStatusType status, Object response) {
		this.status = status;
		this.message = null;
		this.response = response;
	}

	protected ResponseWriter(ResponseStatusType status, String message, Object response) {
		this.status = status;
		this.message = message;
		this.response = response;
	}

	public static Response ok(Object entity) {
		return write(entity, ResponseStatus.OK);
	}

	public static Response ok(String message) {
		return write(null, ResponseStatus.OK, message);
	}

	public static Response write(String message, ResponseStatusType status) {
		return write(null, status, message);
	}

	public static Response write(Object entity, ResponseStatusType status) {
		ResponseWriter response = new ResponseWriter(status, null, entity);
		return response.build();
	}

	public static Response write(Object entity, ResponseStatusType status, String message) {
		ResponseWriter response = new ResponseWriter(status, message, entity);
		return response.build();
	}

	protected Response build() {
		Response.ResponseBuilder b = Response.status(status.getHttpStatusCode());
		b.entity(new SerializableEntity(this));
		return b.type(MediaType.APPLICATION_JSON).build();
	}

	private ResponseStatusType status;
	private String message;
	private Object response;
}