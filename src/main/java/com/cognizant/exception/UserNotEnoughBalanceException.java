package com.cognizant.exception;

public class UserNotEnoughBalanceException extends RuntimeException {

	public UserNotEnoughBalanceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotEnoughBalanceException(String message) {
		super(message);
	}

	public UserNotEnoughBalanceException(Throwable cause) {
		super(cause);
	}
}
