package com.cognizant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {
	// Add an exception handler using @ExceptionHandler

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRestExceptionHandler.class);
	
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException ex) {
		UserErrorResponse error = new UserErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				System.currentTimeMillis());
		
		getLogger().debug("handling 404 error on user not found");

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(TransactionNotFoundException ex) {
		UserErrorResponse error = new UserErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				System.currentTimeMillis());

		getLogger().debug("handling 404 error on transaction not found");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(BankTransactionValidationException ex) {
		UserErrorResponse error = new UserErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
				System.currentTimeMillis());
		getLogger().debug("handling 500 - Internal Server error on transaction validation");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(ForeignKeyException ex) {
		UserErrorResponse error = new UserErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
				System.currentTimeMillis());
		getLogger().debug("handling 500 - Internal Server Error on Foreign key violation");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserNotEnoughBalanceException ex) {
		UserErrorResponse error = new UserErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
				System.currentTimeMillis());
		getLogger().debug("handling 500 - Internal Server Error, User doesn't have enough balance");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Add another exception handle... to catch any exception (Catch ALL).
	@ExceptionHandler

	public ResponseEntity<UserErrorResponse> handleException(Exception ex) {
		UserErrorResponse error = new UserErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				System.currentTimeMillis());
		getLogger().debug("handling 400 bad request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	public static Logger getLogger() {
		return LOGGER;
	}
}
