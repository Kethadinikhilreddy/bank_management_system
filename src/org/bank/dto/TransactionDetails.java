package org.bank.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionDetails {
	
	private int transactionId;
	private LocalDate date;
	private LocalTime time;
	private String transactionType;
	private double transactionAmount;
	private double balanceAmount;
	private long accountNumber;
	
	
	
	public TransactionDetails() {
		super();
	}



	public TransactionDetails(int transactionId, LocalDate date, LocalTime time, String transactionType,
			double transactionAmount, double balanceAmount, long accountNumber) {
		super();
		this.transactionId = transactionId;
		this.date = date;
		this.time = time;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.balanceAmount = balanceAmount;
		this.accountNumber = accountNumber;
	}
	
	


	public int getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public LocalTime getTime() {
		return time;
	}



	public void setTime(LocalTime time) {
		this.time = time;
	}



	public String getTransactionType() {
		return transactionType;
	}



	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}



	public double getTransactionAmount() {
		return transactionAmount;
	}



	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}



	public double getBalanceAmount() {
		return balanceAmount;
	}



	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}



	public long getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}



	@Override
	public String toString() {
		return "TransactionDetails [transactionId=" + transactionId + ", date=" + date + ", time=" + time
				+ ", transactionType=" + transactionType + ", transactionAmount=" + transactionAmount
				+ ", balanceAmount=" + balanceAmount + ", accountNumber=" + accountNumber + "]";
	}
	
	
	
	

}
