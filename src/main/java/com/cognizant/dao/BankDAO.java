package com.cognizant.dao;

import java.util.List;

import com.cognizant.entity.TransactionDetail;
import com.cognizant.entity.TransactionResult;
import com.cognizant.entity.User;

public interface BankDAO {
	public List<User> getUsers();
	public User getUser(Long accNo);
	public void saveUser(User theUser);
	public void saveTransactionDetail(TransactionDetail transDetail, Long accNo);
	public List<TransactionDetail> getTransactionDetails(User theUser);
	public List<TransactionResult> getCustomTransactionDetails(User theUser);
	public User getUser(String accName);
	public List<TransactionResult> getCustomTransactionDetails(Long transno);
}
