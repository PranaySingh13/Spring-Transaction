package com.gk.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionDaoImpl extends JdbcDaoSupport implements TransactionDao {

	private DataSourceTransactionManager transactionManager;

	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public String transferFunds(String fromAccount, String toAccount, int transfer_Amount) {
		String status = "";
		TransactionDefinition tx_Def = new DefaultTransactionDefinition();
		TransactionStatus tx_Status = transactionManager.getTransaction(tx_Def);
		try {
			withdraw(fromAccount, transfer_Amount);
			deposit(toAccount, transfer_Amount);
			transactionManager.commit(tx_Status);
			status = "Transaction Success";
		} catch (Exception e) {
			transactionManager.rollback(tx_Status);
			status = "Transation Failure";
			e.printStackTrace();
		}
		return status;
	}

	public void withdraw(String account, int withdraw_Amount) {
		getJdbcTemplate().execute(
				"update ACCOUNT set BALANCE = BALANCE - " + withdraw_Amount + " where ACCNO = '" + account + "'");
	}

	public void deposit(String account, int deposit_Amount) {
		getJdbcTemplate().execute(
				"update ACCOUNT set BALANCE = BALANCE + " + deposit_Amount + " where ACCNO = '" + account + "'");
	}

}
