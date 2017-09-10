package net.rest.exception;

public class InvalidRequestException extends RuntimeException {
	public InvalidRequestException(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private static final long serialVersionUID = 1L;
	private String message;

}
