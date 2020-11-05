package com.gk.dao;

public interface TransactionDao {

	public String transferFunds(String fromAccount, String toAccount, int transfer_Amount);

	public String deposit(String accNo, int deposit_Amount);
	
	public String withdraw(String accNo, int withdraw_Amount);
}
