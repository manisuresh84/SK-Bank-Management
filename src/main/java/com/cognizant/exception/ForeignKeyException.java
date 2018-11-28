package com.cognizant.exception;

public class ForeignKeyException extends RuntimeException {

	public ForeignKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForeignKeyException(String message) {
		super(message);
	}

	public ForeignKeyException(Throwable cause) {
		super(cause);
	}
}
