package com.gk.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gk.dao.TransactionDao;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/gk/resources/applicationContext.xml");
		TransactionDao tx_Dao = (TransactionDao) context.getBean("transactionDao");

		String depositStatus1 = tx_Dao.deposit("abc123", 10000);
		System.out.println(depositStatus1);
		String depositStatus2 = tx_Dao.deposit("xyz123", 10000);
		System.out.println(depositStatus2);

		String withdrawStatus1 = tx_Dao.withdraw("abc123", 5000);
		System.out.println(withdrawStatus1);
		String withdrawStatus2 = tx_Dao.withdraw("xyz123", 5000);
		System.out.println(withdrawStatus2);

		String transactionStatus = tx_Dao.transferFunds("abc123", "xyz123", 500);// assuming these two accounts existed
		System.out.println(transactionStatus);

	}

}
