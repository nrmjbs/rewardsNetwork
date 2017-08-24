package com.rewardsnetwork.exception;

public class ApplicationException extends RewardsNetworkException {

	private static final long serialVersionUID = 4475758177187209203L;

	public ApplicationException(String message) {

		super(message);

	}

	public ApplicationException(String message, Throwable cause) {

		super(message, cause);

	}

}
