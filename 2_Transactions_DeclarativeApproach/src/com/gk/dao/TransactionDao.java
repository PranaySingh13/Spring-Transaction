package com.gk.dao;

public interface TransactionDao {

	public String transferFunds(String fromAccount, String toAccount, int transfer_Amount);
}
