package com.cognizant.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.TransactionDetail;
import com.cognizant.entity.TransactionResult;
import com.cognizant.entity.User;
import com.cognizant.exception.UserRestExceptionHandler;
import com.github.fluent.hibernate.transformer.FluentHibernateResultTransformer;

@Repository
public class BankDAOImpl implements BankDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getUsers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<User> theQuery = currentSession.createQuery("from User order by accountNumber", User.class);

		// execute query and get result list
		List<User> users = theQuery.getResultList();

		// return the results
		return users;
	}

	@Override
	public User getUser(Long accNo) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		User theUser = currentSession.get(User.class, accNo);

		return theUser;
	}

	@Override
	public void saveUser(User theUser) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/upate the customer ... finally LOL
		currentSession.saveOrUpdate(theUser);

	}

	@Override
	public void saveTransactionDetail(TransactionDetail transDetail, Long accNo) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Load User class
		User user = currentSession.load(User.class, accNo);
		if (user != null) {
			transDetail.setUser(user);
			currentSession.save(transDetail);
		}
	}

	@Override
	public List<TransactionDetail> getTransactionDetails(User theUser) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<TransactionDetail> theQuery = currentSession.createQuery(
				"from TransactionDetail where user.accountNumber = :accNo order by transactionId",
				TransactionDetail.class);

		theQuery.setParameter("accNo", theUser.getAccountNumber());

		// execute query and get result list
		List<TransactionDetail> transDetails = theQuery.getResultList();

		// return the results
		return transDetails;
	}

	@Override
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<TransactionResult> getCustomTransactionDetails(User theUser) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		String sql = "select t.transaction_id as transactionId, t.transaction_description as transactionDescription, "
				+ "t.transaction_type as transactionType, t.transaction_amount as transactionAmount, t.account_number as accountNumber from TRANSACTION_DETAILS t where t.account_number ="
				+ theUser.getAccountNumber();

		List<TransactionResult> transResult = currentSession.createNativeQuery(sql)
				.setResultTransformer(new FluentHibernateResultTransformer(TransactionResult.class)).list();

		return transResult;
	}

	@Override
	public User getUser(String accName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		String sql = "select u.ACCOUNT_NUMBER as accountNumber, u.ACCOUNT_TYPE as accountType, u.ACCOUNT_HOLDER_NAME as accountHolderName, u.ACCOUNT_BALANCE as accountBalance from USER_DETAILS u";

		User theUser = (User) currentSession.createNativeQuery(sql)
				.setResultTransformer(new FluentHibernateResultTransformer(User.class)).list();

		// now retrieve/read from database using the primary key
		// User theUser = currentSession.get(User.class, accName);

		return theUser;

	}

	@Override
	public List<TransactionResult> getCustomTransactionDetails(Long transno) {

		Session currentSession = sessionFactory.getCurrentSession();

		/*
		 * // create a query ... sort by last name Query<TransactionDetail>
		 * theQuery = currentSession.createQuery(
		 * "from TransactionDetail where transactionId = :transNo order by transactionId"
		 * , TransactionDetail.class);
		 * 
		 * theQuery.setParameter("transNo", transno);
		 * 
		 * // execute query and get result list List<TransactionDetail>
		 * transDetails = theQuery.getResultList();
		 */

		String sql = "select t.transaction_id as transactionId, t.transaction_description as transactionDescription, t.transaction_type as transactionType, t.transaction_amount as transactionAmount, t.account_number as accountNumber from TRANSACTION_DETAILS t where t.transaction_id ="
				+ Long.toString(transno);

		List<TransactionResult> transResult = currentSession.createNativeQuery(sql)
				.setResultTransformer(new FluentHibernateResultTransformer(TransactionResult.class)).list();

		return transResult;
	}

}
