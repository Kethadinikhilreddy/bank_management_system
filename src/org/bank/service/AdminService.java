package org.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.bank.dao.AdminDAO;
import org.bank.dao.CustomerDAO;
import org.bank.dto.CustomerDetails;
import org.bank.exceptions.CustomerInvalidDataException;

public class AdminService {
	
	Scanner s=new Scanner(System.in);
	AdminDAO adminDAO=new AdminDAO();
	CustomerService customerService=new CustomerService();
	
	public void adminLogin() {
		System.out.println("Enter Admin EmailId");
		String email="";
		while(true) {
			 email=s.next();
			try {
				if(email.contains("@gmail.com"))
					break;
				else
					throw new CustomerInvalidDataException("Invalid Email");
			}
			catch(CustomerInvalidDataException e) {
				System.out.println("Invalid EmailId \nPlease Re-Enter EmailId");
			}
		}
		
		System.out.println("Enter Password");
		String password=s.next();
		
		if(adminDAO.selectAdminDetailsUsingEmailAndPassword(email, password)) {
			System.out.println("Login Successfull");
			adminOperations();		
		}
		else {
			System.err.println("Invalid Data");
		}
	}
	
	
	private void adminOperations() {
		System.out.println("Enter "
				+ "\n  1. For Accept or Reject Account Request"
				+ "\n  2. For Accept or Reject Account Closing");
		switch (s.nextInt()) {
		case 1:
			accountRequest();
			break;
		case 2:
			
			break;	
		default:
			System.err.println("Invalid Option");
			break;
		}
		
	}
	
	private void accountRequest() {
		List<CustomerDetails> li=customerService.selectAllNotHavingAccountNumberAndPin();
		if(li!=null) {
			System.out.println("---------- CUSTOMER DETAILS ----------");
			int count=1;
			for(CustomerDetails cd:li) {
				System.out.println("SNO           : "+count++);
				System.out.println("ID            : "+cd.getId());
				System.out.println("NAME          : "+cd.getCustomerName());
				System.out.println("AADHAR NUMBER : "+cd.getAadharNumber());
				System.out.println("MOBILE NUMBER : "+cd.getMobileNumber());
				System.out.println("GENDER        : "+cd.getGender());
				
				System.out.println("-------------------------------------");
			}
			
			System.out.println("Enter SNO of Customer ");
			int adminSno=s.nextInt();
			CustomerDetails cd=li.get(adminSno-1);
			if(cd!=null) {
					System.out.println("Enter "
							+ "\n 1. For Accept "
							+ "\n 2. For Reject");
					switch (s.nextInt()) {
					case 1:
						acceptRequest(cd);
						break;
					case 2:
						if(customerService.deleteUsingAadhar(cd.getAadharNumber())) 
							System.out.println("Deleted Successfully");
						else	
							System.err.println("SERVER ERROR");
						break;

					default:
						System.out.println("Invalid Option");
						break;
					}
			}
			else {
				System.err.println("Invalid SNO");
			}
		}
		else {
			System.out.println("NO DATA TO PRINT");
		}
	}
	
	
	private void acceptRequest(CustomerDetails cd) {
		List<CustomerDetails> listOfCustomers=customerService.getAllCustomerDetails();
		while(true) {
			long accountNumber=(long)(Math.random()*9000000)+1000000;
			int pin=(int) ((Math.random()*9000)+1000);
			if(listOfCustomers!=null) {
				long accountCount=listOfCustomers.stream().filter((customer->customer.getAccountNumber()==accountNumber)).count();
				if(accountCount==0) {
					long pinCount=listOfCustomers.stream().filter((customer->customer.getPinNumber()==pin)).count();
					if(pinCount==0) {
						if(customerService.setAccountNumberAndPin(accountNumber, pin,cd.getAadharNumber()))
							System.out.println("Updated Successfully");
						else
							System.err.println(" SERVER ERROR ");
					}
					break;
				}
			}
			else {
				if(customerService.setAccountNumberAndPin(accountNumber, pin, cd.getAadharNumber()))
					System.out.println("Updated Successfully");
				else
					System.err.println(" SERVER ERROR ");
			}
		}
	}

}
