package com.rewardsnetwork.exception;

public abstract class RewardsNetworkException extends RuntimeException {

	private static final long serialVersionUID = 4475758177187209203L;

	public RewardsNetworkException(String message) {

		super(message);

	}

	public RewardsNetworkException(String message, Throwable cause) {

		super(message, cause);

	}

}
