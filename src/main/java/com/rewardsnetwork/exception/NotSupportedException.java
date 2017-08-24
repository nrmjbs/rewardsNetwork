package com.rewardsnetwork.exception;

public class NotSupportedException extends ApplicationException {

	private static final long serialVersionUID = -4162439674017180909L;

	public NotSupportedException(String message) {

		super(message);

	}

	public NotSupportedException(String message, Throwable cause) {

		super(message, cause);

	}

}
