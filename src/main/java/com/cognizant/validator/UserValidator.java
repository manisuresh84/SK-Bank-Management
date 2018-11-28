package com.cognizant.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cognizant.entity.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNumber", "error.accountNumber",
				"AccountNumber is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transactionAmount", "error.transactionAmount",
				"TransactionAmount is Required");

		if (user.getAccountNumber() != null
				&& !this.validateAccNo(String.valueOf(user.getAccountNumber()))) {
			errors.rejectValue("accountNumber", "negativeValue", "Invalid Account Number");
		}

	}

	private boolean validateAccNo(String accNo) {
		return accNo.matches("\\d{16}");
	}


}
