package org.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.bank.dao.TransactionDAO;
import org.bank.dto.TransactionDetails;

public class TransactionService {
	
	TransactionDAO transactionDAO=new TransactionDAO();
	
	public void insertTransactionDetails(TransactionDetails transactionDetails) {
		
		if(!transactionDAO.insertTransactionDetails(transactionDetails))
			System.out.println("SERVER ERROR");
	}
	
	public List<TransactionDetails> displayTransactions(long accountNumber) {
		 List<TransactionDetails> al= transactionDAO.getTransactionDetailsByUsingAccountNumber(accountNumber);
		 return al;
	}

}
