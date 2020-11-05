package com.gk.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class TransactionDaoImpl extends JdbcDaoSupport implements TransactionDao {

	@Override
	public String transferFunds(String fromAccount, String toAccount, int transfer_Amount) {
		String status = "";
		int debitRowCount = getJdbcTemplate().update(
				"update ACCOUNT set BALANCE = BALANCE - " + transfer_Amount + " where ACCNO = '" + fromAccount + "'");
		
		//float f=100/0; for checking atomicity property
		
		int creditRowCount = getJdbcTemplate().update(
				"update ACCOUNT set BALANCE = BALANCE + " + transfer_Amount + " where ACCNO = '" + toAccount + "'");

		if (debitRowCount == 1 && creditRowCount == 1) {
			status = "Transaction Success";
		} else {
			status = "Transaction Failure";
		}
		return status;
	}

}
