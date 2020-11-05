package com.gk.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class TransactionDaoImpl extends JdbcDaoSupport implements TransactionDao {

	@Transactional
	@Override
	public String transferFunds(String fromAccount, String toAccount, int transfer_Amount) {
		String status = "";
		int debitRowCount = getJdbcTemplate().update(
				"update ACCOUNT set BALANCE = BALANCE - " + transfer_Amount + " where ACCNO = '" + fromAccount + "'");
		
		// float f=100/0; for checking atomicity property
		
		int creditRowCount = getJdbcTemplate().update(
				"update ACCOUNT set BALANCE = BALANCE + " + transfer_Amount + " where ACCNO = '" + toAccount + "'");

		if (debitRowCount == 1 && creditRowCount == 1) {
			status = "Transaction Success";
		} else {
			status = "Transaction Failure";
		}
		return status;
	}

	@Transactional
	@Override
	public String deposit(String accNo, int deposit_Amount) {
		String status="";
		int depositRowCount=getJdbcTemplate().update(
				"update ACCOUNT set BALANCE = BALANCE + " + deposit_Amount + " where ACCNO = '" + accNo + "'");
		if(depositRowCount==1) {
			status="Transaction Amount Deposited Successfully in :'"+accNo+"'";
		}else {
			status="Transation Amount Deposition Got Failed";
			throw new RuntimeException("Insufficient Details in the Transation");
		}
		return status;
	}

	@Transactional
	@Override
	public String withdraw(String accNo, int withdraw_Amount) {
		String status="";
		int withdrawRowCount=getJdbcTemplate().update(
				"update ACCOUNT set BALANCE = BALANCE - " + withdraw_Amount + " where ACCNO = '" + accNo + "'");
		if(withdrawRowCount==1) {
			status="Transaction Amount Withdrawal Successfully from:'"+accNo+"'";
		}else {
			status="Transation Amount Withdrawal Got Failed";
			throw new RuntimeException("Insufficient Details in the Transation");
		}
		return status;
	}

}
