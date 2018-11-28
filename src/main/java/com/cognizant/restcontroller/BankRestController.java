package com.cognizant.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.entity.TransactionDetail;
import com.cognizant.entity.TransactionResult;
import com.cognizant.entity.User;
import com.cognizant.exception.TransactionNotFoundException;
import com.cognizant.exception.UserNotEnoughBalanceException;
import com.cognizant.exception.UserNotFoundException;
import com.cognizant.exception.UserRestExceptionHandler;
import com.cognizant.service.BankService;
import com.cognizant.validator.TransactionDetailsValidator;
import com.cognizant.validator.UserValidator;

@RestController
@RequestMapping("/api")
public class BankRestController {

	@Autowired
	private BankService bankService;

	//@Autowired
	TransactionDetailsValidator transactionDetailsValidator;
	
	//@Autowired
	UserValidator userValidator;

	@GetMapping("/users")
	public List<User> getUsers() {
		return bankService.getUsers();
	}

	@GetMapping("/users/{accNo}")
	public User getUser(@PathVariable Long accNo) {
		UserRestExceptionHandler.getLogger().info("Inside get User");
		User theUser = bankService.getUser(accNo);
		if (theUser == null) {
			throw new UserNotFoundException("User not found in database -" + accNo);
		}
		UserRestExceptionHandler.getLogger().info("User Data from Database : " + theUser.toString());
		return theUser;
	}
	
	@GetMapping("/user/{accName}")
	public User getUser(@PathVariable String accName) {
		UserRestExceptionHandler.getLogger().info("Inside get User");
		User theUser = bankService.getUser(accName);
		if (theUser == null) {
			throw new UserNotFoundException("User not found in database -" + accName);
		}
		UserRestExceptionHandler.getLogger().info("User Data from Database : " + theUser.toString());
		return theUser;
	}

	@PostMapping("/users")
	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User addUser(@RequestBody User user) {
		UserRestExceptionHandler.getLogger().info("Inside Add User");
		bankService.saveUser(user);
		UserRestExceptionHandler.getLogger()
				.info("The given User [" + user.toString() + "] added successfully into database");
		return user;
	}

	@PostMapping("/transactions/{accno}")
	public TransactionDetail addTransaction(@RequestBody TransactionDetail transDetail, @PathVariable Long accno,
			BindingResult bindingResult) {
		UserRestExceptionHandler.getLogger().info("Inside Add Transaction");

		try {
			transactionDetailsValidator.validate(transDetail, bindingResult);
			if (bindingResult.hasErrors()) {
				UserRestExceptionHandler.getLogger().info("Transaction Details Validator Failed!!");
			} else {
				bankService.saveTransactionDetail(transDetail, accno);
			}
		} catch (Exception e) {
			throw new UserNotEnoughBalanceException("User [" + accno
					+ "] doesn't have enough balance for withdraw! or minimum balance is required for your account!");
		}

		return transDetail;
	}

	@GetMapping("/transactions/{accno}")
	public List<TransactionDetail> getTransactions(@PathVariable Long accno) {
		UserRestExceptionHandler.getLogger().info("Inside getTransactions AccNo [" + accno + "]");
		User theUser = bankService.getUser(accno);

		if (theUser == null) {
			throw new UserNotFoundException("User not found in database -" + accno);
		}

		List<TransactionDetail> listTrans = bankService.getTransactionDetails(theUser);
		if (listTrans.size() == 0) {
			throw new TransactionNotFoundException(
					"User[" + theUser.getAccountNumber() + "] doesn't have any transaction!");
		}

		return bankService.getTransactionDetails(theUser);
	}

	@GetMapping("/customtransactions/{accno}")
	public List<TransactionResult> getCustomTransactions(@PathVariable Long accno) {
		UserRestExceptionHandler.getLogger().info("Inside getCustomTransactions AccNo [" + accno + "]");
		User theUser = bankService.getUser(accno);

		if (theUser == null) {
			throw new UserNotFoundException("User not found in database -" + accno);
		}

		List<TransactionResult> listTrans = bankService.getCustomTransactionDetails(theUser);
		if (listTrans.size() == 0) {
			throw new TransactionNotFoundException(
					"User[" + theUser.getAccountNumber() + "] doesn't have any transaction!");
		}

		return bankService.getCustomTransactionDetails(theUser);
	}

	@PostMapping("/performtransaction/{accno}")
	public Double initiateTransaction(@RequestBody TransactionDetail transDetail, @PathVariable Long accno) {
		
		UserRestExceptionHandler.getLogger().info("Inside initiateTransaction AccNo [" + accno + "] Transaction Detail [" + transDetail.toString() +"]");
		try {
			bankService.saveTransactionDetail(transDetail, accno);
		} catch (Exception e) {
			throw new UserNotEnoughBalanceException("User [" + accno
					+ "] doesn't have enough balance for withdraw! or minimum balance is required for your account!");
		}

		User theUser = bankService.getUser(accno);

		return theUser.getAccountBalance();
	}
	
	@GetMapping("/customtransaction/{transno}")
	public List<TransactionResult> getCustomTransaction(@PathVariable Long transno) {
		UserRestExceptionHandler.getLogger().info("Inside getCustomTransactions AccNo [" + transno + "]");
		List<TransactionResult> listTrans = bankService.getCustomTransactionDetails(transno);
		if (listTrans.size() == 0) {
			throw new TransactionNotFoundException(
					"Transaction ID [" + transno + "] doesn't have any transaction!");
		}

		return listTrans;
	}


}
