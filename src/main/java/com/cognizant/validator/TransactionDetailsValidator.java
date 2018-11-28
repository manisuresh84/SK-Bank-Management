package com.cognizant.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cognizant.entity.TransactionDetail;

public class TransactionDetailsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TransactionDetail.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TransactionDetail transactionDetails = (TransactionDetail) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNumber", "error.accountNumber",
				"AccountNumber is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transactionAmount", "error.transactionAmount",
				"TransactionAmount is Required");

		if (transactionDetails.getUser().getAccountNumber() != null
				&& !this.validateAccNo(String.valueOf(transactionDetails.getUser().getAccountNumber()))) {
			errors.rejectValue("accountNumber", "negativeValue", "Invalid Account Number");
		}

		if (transactionDetails.getTransactionAmount() != null && transactionDetails.getTransactionAmount() <= 0.0D) {
			errors.rejectValue("transactionAmount", "negativeValue", "Invalid Transaction Amount");
		}

	}

	private boolean validateAccNo(String accNo) {
		return accNo.matches("\\d{16}");
	}

}
