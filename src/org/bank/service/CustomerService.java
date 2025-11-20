package org.bank.service;

import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.bank.dao.CustomerDAO;
import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;
import org.bank.exceptions.CustomerInvalidDataException;

public class CustomerService {
	
	CustomerDAO customerDAO=new CustomerDAO();
	CustomerDetails cd=new CustomerDetails();
	TransactionService transactionService=new TransactionService();
	TransactionDetails transactionDetails=new TransactionDetails();
	
	Scanner s=new Scanner(System.in);
	
	
	
	//Customer Registration Method
	public void customerRegistration() {
		
		List<CustomerDetails> li=customerDAO.getAllCustomerDetails();
		
		System.out.println("Enter the Customer Details");
		
		//Setting the Name
		System.out.println("Enter the Name");
		while(true) {
			String name=s.next();
			try {
				if(name.matches("^[A-Za-z ]+$")) {
					cd.setCustomerName(name);
					break;
				}
				else {
					throw new CustomerInvalidDataException("Invalid Name");
				}
					
			}
			catch(CustomerInvalidDataException e) {
				System.out.println(e.getMessage());
				System.out.println("Please Re-Enter Again");
			}
		}
		
		//Setting the EmailId
		System.out.println("Enter the Email Id");
		while(true) {
			String email=s.next();
			try {
				if(email.contains("@gmail.com")) {
					if(li==null) {
						cd.setEmailId(email);
						break;
					}
					else {
						long emailCount=li.stream().filter((customer->customer.getEmailId().equals(email))).count();
						if(emailCount!=0) {
							throw new CustomerInvalidDataException("EmailId Already Exists");
						}
						else {
							cd.setEmailId(email);
							break;
						}
					}
				}
				else {
					throw new CustomerInvalidDataException("Invalid EmailId");
				}
				
			}
			catch(CustomerInvalidDataException e) {
				System.err.println(e.getMessage());
				System.out.println("Please Re-Enter Again");
			}
		}
		
		//Setting Mobile Number
		System.out.println("Enter the Mobile Number");
		while(true) {
			try {
				long mobileNumber=s.nextLong();
				if(mobileNumber>=6000000000l && mobileNumber<=9999999999l) {
					long mobileCount=li.stream().filter((customer->customer.getMobileNumber()==mobileNumber)).count();
					if(mobileCount!=0) {
						throw new CustomerInvalidDataException("Mobile Number Already Exists");
					}
					else {
						cd.setMobileNumber(mobileNumber);
						break;
					}
				}
				else {
					throw new CustomerInvalidDataException("Invalid Mobile Number");
				}
			}
			catch(CustomerInvalidDataException e) {
				System.out.println(e.getMessage());
				System.out.println("Please Re-Enter Again");
			}
		}
		
		//Setting AadharNumber
		System.out.println("Enter the Aadhar Number");
		while(true) {
			try {
				 long aadharNumber=s.nextLong();
				if((aadharNumber+"").length()==12) {
					long aadharCount=li.stream().filter((customer->customer.getAadharNumber()==aadharNumber)).count();
					if(aadharCount!=0) {
						throw new CustomerInvalidDataException("Aadhar Number Already Exists");
					}
					else {
						cd.setAadharNumber(aadharNumber);
						break;
					}
				}
				else {
					throw new CustomerInvalidDataException("Invalid Aadhar Number");
				}
				
			}
			catch(CustomerInvalidDataException e) {
				System.out.println(e.getMessage());
				System.out.println("Please Re-Enter Again");
			}
		}
		
		//Setting PanNumber
		System.out.println("Enter the PAN Number");
		while(true) {
			try {
				String panNumber=s.next();
				if(panNumber.matches("^[A-Z]{5}[0-9]{4}[A-Z]{1}$")) {
					long panCount=li.stream().filter((customer->customer.getPanNumber().equals(panNumber))).count();
					if(panCount!=0) {
						throw new CustomerInvalidDataException("Pan Number Already Exists");
					}
					else {
						cd.setPanNumber(panNumber);
						break;
					}
				}
				else {
					throw new CustomerInvalidDataException("Invalid Pan Number");
				}
			}
			catch(CustomerInvalidDataException e) {
				System.out.println(e.getMessage());
				System.out.println("Please Re-Enter Again");
			}
		}
		
		System.out.println("Enter the Address");
		String address=s.next();
		System.out.println("Enter the Designation");
		String designation=s.next();
		
		//Setting Gender
		System.out.println("Enter the Gender");
		while(true) {
			String gender=s.next();
			try {
				if(gender.equalsIgnoreCase("Male")||gender.equalsIgnoreCase("Female")|| gender.equalsIgnoreCase("Others")) {
					cd.setGender(gender);
					break;
				}
				else {
					throw new CustomerInvalidDataException("Invalid Gender");
				}
			}
			catch(CustomerInvalidDataException e) {
				System.out.println(e.getMessage());
				System.out.println("Please Re-Enter Again");
			}
		}
		
		System.out.println("Select Type of Account\n1.Savings\n2.Current");
		String typeOfAccount=null;
		int choice=s.nextInt();
		if(choice==1) {
			typeOfAccount="Savings";
		}
		else if(choice==2) {
			typeOfAccount="Current";
		}
		else {
			System.out.println("Invalid Choice..");
		}
		
		//Setting Amount
		System.out.println("Enter the Amount");
		while(true) {
			double amount=s.nextDouble();
			try {
				if(amount>=0) {
					cd.setAmount(amount);
					break;
				}
				else {
					throw new CustomerInvalidDataException("Invalid Amount");
				}
			}
			catch(CustomerInvalidDataException e) {
				System.out.println(e.getMessage());
				System.out.println("Please Re-Enter Again");
			}
		}
		
		
		
		cd.setAddress(address);
		cd.setDesignation(designation);
		cd.setTypeOfAccount(typeOfAccount);
		cd.setIfscCode("JSP1919");
		cd.setBranch("JNTUH");
		
		customerDAO.insertCustomerDetails(cd);
	}
	
	
	//Customer Login Method
	
	public void customerLogin() {
		System.out.println("Enter EmailId or MobileNumber");
		String emailOrMobile=s.next();
		System.out.println("Enter Your Pin");
		int pin=s.nextInt();
		CustomerDetails login=customerDAO.getCustomerDetailUsingEmailOrMobile(emailOrMobile, pin);
		if(login!=null) {
			if(login.getGender().equalsIgnoreCase("Male")) {
				System.out.println("WELCOME MR. "+login.getCustomerName());
				customerOperations(login.getAccountNumber());
				
			}
			else if(login.getGender().equalsIgnoreCase("Female")) {
				System.out.println("WELCOME MISS. "+login.getCustomerName());
				customerOperations(login.getAccountNumber());
			}
			else {
				System.out.println("WELCOME "+login.getCustomerName());
				customerOperations(login.getAccountNumber());
			}
		}
		else {
			System.err.println("Invalid Data");
		}
		
	}
	
	
	//Customer Operations Method...
	public void customerOperations(long databaseAccountNumber) {
		while(true) {
			System.out.println("Enter \n 1. For Credit"
					+"\n 2. For Debit"
					+"\n 3. For Check Balance"
					+"\n 4. For Statement"
					+"\n 5. For Close Account"
					+"\n 6. For Change PIN "
					+"\n 7. Exit");

			
			switch (s.nextInt()) {
			case 1:
				System.out.println("Credit");
				creditOperation(databaseAccountNumber);
				break;
			case 2:
				System.out.println("Debit");
				debitOperation(databaseAccountNumber);
				break;
			case 3:
				System.out.println("Check Balance");
				checkingBalance(databaseAccountNumber);
				break;
			case 4:
				System.out.println("Statement");
				printStatements(databaseAccountNumber);
				break;
			case 5:
				System.out.println("Close Account");
				break;
			case 6:
				System.out.println("Change PIN");
				updatePin(databaseAccountNumber);
				break;
			case 7:
				return;
			default:
				System.err.println("Invalid Option");
				break;
			}
		}
	}
	
	
	//Debit Operation Method
	public void debitOperation(long databaseAccountNumber) {
		System.out.println("Enter AccountNumber");
		long accountNumber=s.nextLong();
		CustomerDetails cd=customerDAO.getCustomerAmountByUsingAccountNumber(accountNumber);
		if(cd!=null && accountNumber==databaseAccountNumber) {
			System.out.println("Enter Amount to Withdraw");
			double amount=s.nextDouble();
			if(amount<=cd.getAmount()) {
				double balanceAmount=cd.getAmount()-amount;
				if(customerDAO.setCustomerBalanceAmount(accountNumber, balanceAmount)) {
					transactionDetails.setAccountNumber(accountNumber);
					transactionDetails.setTransactionAmount(amount);
					transactionDetails.setBalanceAmount(balanceAmount);
					transactionDetails.setDate(LocalDate.now());
					transactionDetails.setTime(LocalTime.now());
					transactionDetails.setTransactionType("Debit");
					
					transactionService.insertTransactionDetails(transactionDetails);
					
					System.out.println("Amount Debited Successfully");
				}
				else
					System.err.println("ERROR 404");
			}
			else {
				System.out.println("InSufficent Amount");
			}
		}
		else {
			System.out.println("Invalid AccountNumber");
		}
	}
	
	//Credit Operation Method
	public void creditOperation(long databaseAccountNumber) {
		System.out.println("Enter AccountNumber");
		long accountNumber=s.nextLong();
		CustomerDetails cd=customerDAO.getCustomerAmountByUsingAccountNumber(accountNumber);
		if(cd!=null && accountNumber==databaseAccountNumber) {
			System.out.println("Enter Amount to Credit");
			double amount=s.nextDouble();
			double balanceAmount=cd.getAmount()+amount;
			if(customerDAO.setCustomerBalanceAmount(accountNumber, balanceAmount)) {
				transactionDetails.setAccountNumber(accountNumber);
				transactionDetails.setTransactionAmount(amount);
				transactionDetails.setBalanceAmount(balanceAmount);
				transactionDetails.setDate(LocalDate.now());
				transactionDetails.setTime(LocalTime.now());
				transactionDetails.setTransactionType("Credit");
				
				transactionService.insertTransactionDetails(transactionDetails);
				System.out.println("Amount Credited Successfully");
				
			}
			else
				System.err.println("ERROR 404");
		}
		else {
			System.out.println("Invalid AccountNumber");
		}

		
	}
	
	
	//Checking Balance
	public void checkingBalance(long databaseAccountNumber) {
		System.out.println("Enter AccountNumber");
		long accountNumber=s.nextLong();
		CustomerDetails cd=customerDAO.getCustomerAmountByUsingAccountNumber(accountNumber);
		if(cd!=null && accountNumber==databaseAccountNumber) {
			System.out.println("Available Balance :"+cd.getAmount()+"/-");
		}else {
			System.out.println("Invalid Account Number");
		}
		
	}
	
	//Closing Account
	private void closeAccount(long databaseAccountNumber) {
		System.out.println("Enter AccountNumber");
		long accountNumber=s.nextLong();
		
	}
	
	
	//Updating PIN Number
	public void updatePin(long databaseAccountNumber) {
		System.out.println("Enter Your Account Number");
		long accountNumber=s.nextLong();
		if(accountNumber==databaseAccountNumber) {
			System.out.println("Enter Your New Pin");
			int pin=s.nextInt();
			System.out.println("Re-Enter Your Pin");
			int newPin=s.nextInt();
			if(pin==newPin) {
				if(customerDAO.setPinUsingAccountNumber(accountNumber, newPin))
					System.out.println("Updated Successfully");
				else
					System.out.println("ERROR 404");
			}
			else {
				System.out.println("Pin's MisMatched");
			}
		}
		else
			System.out.println("Invalid AccountNumber");
		
	}
	
	//Printing Bank Statements
	public void printStatements(long databaseAccountNumber) {
		
		System.out.println("Enter Your AccountNumber");
		long accountNumber=s.nextLong();
		
		if(accountNumber==databaseAccountNumber) {
			List<TransactionDetails> al=transactionService.displayTransactions(accountNumber);
			
			if(al!=null) {
			
				System.out.println("----- Transaction Details of A/C NO "+accountNumber+" -----");
				
			
				for(TransactionDetails td:al) {
				
					System.out.println("ID                 :"+td.getTransactionId());
					System.out.println("Type               :"+td.getTransactionType());
					System.out.println("Data               :"+td.getDate());
					System.out.println("Time               :"+td.getTime());
					System.out.println("Transaction Amount :"+td.getTransactionAmount()+"/-");
					System.out.println("Balance Amount     :"+td.getBalanceAmount()+"/-");
					
					System.out.println("--------------------------------------------------");
				}
			}
			else {
				System.out.println("No Transactions Found");
			}
		}
		else {
			System.err.println("Invalid AccountNumber");
		}
	}
	
	
	public List<CustomerDetails> selectAllNotHavingAccountNumberAndPin(){
		List<CustomerDetails> li=customerDAO.selectAllNotHavingAccountNumberAndPin();
		if(li!=null) {
			return li;
		}
		else {
			return null;
		}
	}
	
	public List<CustomerDetails> getAllCustomerDetails(){
		List<CustomerDetails> li=customerDAO.getAllCustomerDetails();
		if(!li.isEmpty()) {
			return li;
		}
		else {
			return null;
		}
	}
	
	public boolean deleteUsingAadhar(long aadharNumber) {
		return customerDAO.deleteUsingAadhar(aadharNumber);
	}
	
	public boolean setAccountNumberAndPin(long accountNumber,int pin,long aadharNumber) {
		return customerDAO.setAccountNumberAndPin(accountNumber, pin, aadharNumber);
	}

	

}
