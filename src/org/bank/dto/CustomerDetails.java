package org.bank.dto;

public class CustomerDetails {
	
	private int id;
	private String customerName;
	private long mobileNumber;
	private long aadharNumber;
	private String panNumber;
	private String emailId;
	private String address;
	private String designation;
	private long accountNumber;
	private int pinNumber;
	private String ifscCode;
	private String branch;
	private String typeOfAccount;
	private double amount;
	private String gender;
	
	
	public CustomerDetails() {
		super();
	}


	public CustomerDetails(int id, String customerName, long mobileNumber, long aadharNumber, String panNumber,
			String emailId, String address, String designation, long accountNumber, int pinNumber, String ifscCode,
			String branch, String typeOfAccount, double amount, String gender) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.emailId = emailId;
		this.address = address;
		this.designation = designation;
		this.accountNumber = accountNumber;
		this.pinNumber = pinNumber;
		this.ifscCode = ifscCode;
		this.branch = branch;
		this.typeOfAccount = typeOfAccount;
		this.amount = amount;
		this.gender = gender;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public long getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public long getAadharNumber() {
		return aadharNumber;
	}


	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}


	public String getPanNumber() {
		return panNumber;
	}


	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public long getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}


	public int getPinNumber() {
		return pinNumber;
	}


	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}


	public String getIfscCode() {
		return ifscCode;
	}


	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}


	public String getBranch() {
		return branch;
	}


	public void setBranch(String branch) {
		this.branch = branch;
	}


	public String getTypeOfAccount() {
		return typeOfAccount;
	}


	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	

}
