package com.rewardsnetwork.exception;

public class ValidationException extends ApplicationException {

	private static final long serialVersionUID = -233875053960398891L;

	public ValidationException(String message) {

		super(message);

	}

	public ValidationException(String message, Throwable cause) {

		super(message, cause);

	}

}
