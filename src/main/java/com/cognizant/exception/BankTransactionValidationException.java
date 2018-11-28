package com.cognizant.exception;

public class BankTransactionValidationException extends RuntimeException {

	public BankTransactionValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BankTransactionValidationException(String message) {
		super(message);
	}

	public BankTransactionValidationException(Throwable cause) {
		super(cause);
	}
}
