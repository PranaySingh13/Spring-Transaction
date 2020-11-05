package com.gk.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gk.dao.TransactionDao;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/gk/resources/applicationContext.xml");
		TransactionDao tx_Dao = (TransactionDao) context.getBean("transactionDao");
		String status = tx_Dao.transferFunds("abc123", "xyz123", 500);//assuming these two accounts existed in database table
		System.out.println(status);
	}

}
