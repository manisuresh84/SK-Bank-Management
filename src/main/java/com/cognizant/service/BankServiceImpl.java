package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.dao.BankDAO;
import com.cognizant.entity.TransactionDetail;
import com.cognizant.entity.TransactionResult;
import com.cognizant.entity.User;

@Service
public class BankServiceImpl implements BankService {

	// need to inject customer dao
	@Autowired
	private BankDAO bankDAO;

	@Override
	@Transactional
	public List<User> getUsers() {
		return bankDAO.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User theUser) {

		bankDAO.saveUser(theUser);
	}

	@Override
	@Transactional
	public User getUser(Long accNo) {
		return bankDAO.getUser(accNo);
	}

	@Override
	@Transactional
	public void saveTransactionDetail(TransactionDetail transDetail, Long accNo) {
		bankDAO.saveTransactionDetail(transDetail, accNo);
	}

	@Override
	@Transactional
	public List<TransactionDetail> getTransactionDetails(User theUser) {
		return bankDAO.getTransactionDetails(theUser);
	}

	@Override
	@Transactional
	public List<TransactionResult> getCustomTransactionDetails(User theUser) {
		return bankDAO.getCustomTransactionDetails(theUser);
	}

	@Override
	@Transactional
	public User getUser(String accName) {
		return bankDAO.getUser(accName);
	}

	@Override
	@Transactional
	public List<TransactionResult> getCustomTransactionDetails(Long transno) {
		return bankDAO.getCustomTransactionDetails(transno);
	}
}
