package org.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bank.dto.CustomerDetails;
import org.dbconnection.DbConnection;

public class CustomerDAO {
	
	private static final String insert_customer_details="insert into bank_customer_details(Customer_Name, Customer_MobileNumber,"
			+ " Customer_AadharNumber, Customer_PanNumber, Customer_EmailId, Customer_Address, Customer_Designation,"
			+ "IfscCode, Branch, TypeOfAccount, Amount, Gender) values (?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String selectAll="select * from bank_customer_details";
	
	private static final String customer_login="select * from bank_customer_details where"
			+ " (Customer_EmailId=? or Customer_MobileNumber=?) and Customer_Pin=?";
	
	private static final String getByUsing_AccountNumber="select* from bank_customer_details where Customer_AccountNumber=?";
	
	private static final String setAmount_ByUsing_AccountNumber="update bank_customer_details set Amount=? where Customer_AccountNumber=?";
	
	private static final String updatePin_Using_AccountNumber="update bank_customer_details set Customer_Pin=? where Customer_AccountNumber=?";
	
	private static final String selectAllNotHavingAccountNumberAndPin="select * from bank_customer_details where Customer_AccountNumber IS NULL and Customer_Pin IS NULL";
	
	private static final String deleteUsingId="delete from bank_customer_details where Customer_AadharNumber=?";
	
	private static final String setAccountNumberAndPin="update bank_customer_details set Customer_AccountNumber=?,Customer_Pin=? where Customer_AadharNumber=?";
	
	
	
	
	public void insertCustomerDetails(CustomerDetails customerDetails) {
		
		try {
			
			PreparedStatement ps=DbConnection.getConnection().prepareStatement(insert_customer_details);
		
			ps.setString(1,customerDetails.getCustomerName());
			ps.setLong(2,customerDetails.getMobileNumber());
			ps.setLong(3,customerDetails.getAadharNumber());
			ps.setString(4, customerDetails.getPanNumber());
			ps.setString(5, customerDetails.getEmailId());
			ps.setString(6,customerDetails.getAddress());
			ps.setString(7, customerDetails.getDesignation());
			ps.setString(8, customerDetails.getIfscCode());
			ps.setString(9, customerDetails.getBranch());
			ps.setString(10, customerDetails.getTypeOfAccount());
			ps.setDouble(11, customerDetails.getAmount());
			ps.setString(12, customerDetails.getGender());
			
			int result=ps.executeUpdate();
			if(result!=0) {
				System.out.println("Customer Registration Successfull...");
			}
			else {
				System.out.println("EROOR--404..");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public List<CustomerDetails> getAllCustomerDetails() {
		List<CustomerDetails> listOfCustomers=new ArrayList<CustomerDetails>();
		try {
			PreparedStatement ps=DbConnection.getConnection().prepareStatement(selectAll);
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					CustomerDetails cd=new CustomerDetails();
					
					cd.setAadharNumber(rs.getLong("Customer_AadharNumber"));
					cd.setPanNumber(rs.getString("Customer_PanNumber"));
					cd.setMobileNumber(rs.getLong("Customer_MobileNumber"));
					cd.setEmailId(rs.getString("Customer_EmailId"));
					cd.setAccountNumber(rs.getLong("Customer_AccountNumber"));
					cd.setPinNumber(rs.getInt("Customer_Pin"));
					
					listOfCustomers.add(cd);
				}
				return listOfCustomers;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public CustomerDetails getCustomerDetailUsingEmailOrMobile(String emailOrMobile,int pin) {
		try {
			PreparedStatement ps=DbConnection.getConnection().prepareStatement(customer_login);
			ps.setString(1, emailOrMobile);
			ps.setString(2, emailOrMobile);
			ps.setInt(3, pin);
			
			
		  	ResultSet rs= ps.executeQuery();
		  	
		  	if(rs.next()) {
		  		CustomerDetails cd=new CustomerDetails();
		  		cd.setCustomerName(rs.getString("Customer_Name"));
		  		cd.setGender(rs.getString("Gender"));
		  		cd.setAccountNumber(rs.getLong("Customer_AccountNumber"));
		  		return cd;
		  	}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public CustomerDetails getCustomerAmountByUsingAccountNumber(long accountNumber) {
		try {
			PreparedStatement ps=DbConnection.getConnection().prepareStatement(getByUsing_AccountNumber);
			ps.setLong(1,accountNumber);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				CustomerDetails cd=new CustomerDetails();
				cd.setAmount(rs.getDouble("Amount"));
				return cd;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean setCustomerBalanceAmount(long accountNumber,double amount) {
		try {
			PreparedStatement ps=DbConnection.getConnection().prepareStatement(setAmount_ByUsing_AccountNumber);
			ps.setDouble(1,amount);
			ps.setLong(2,accountNumber);
			int result=ps.executeUpdate();
			if(result!=0)
				return true;
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
	}
	
	
	public boolean setPinUsingAccountNumber(long accountNumber,int pin) {
		
		try {
			PreparedStatement ps=DbConnection.getConnection().prepareStatement(updatePin_Using_AccountNumber);
			ps.setInt(1, pin);
			ps.setLong(2,accountNumber);
			int result=ps.executeUpdate();
			if(result!=0)
				return true;
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	
	public List<CustomerDetails> selectAllNotHavingAccountNumberAndPin() {
		List<CustomerDetails> li=new ArrayList<CustomerDetails>();
		try {
			PreparedStatement ps=DbConnection.getConnection().prepareStatement(selectAllNotHavingAccountNumberAndPin);
			ResultSet rs=ps.executeQuery();
			
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					CustomerDetails cd=new CustomerDetails();
					cd.setId(rs.getInt("Customer_Id"));
					cd.setCustomerName(rs.getString("Customer_Name"));
					cd.setEmailId(rs.getString("Customer_EmailId"));
					cd.setAadharNumber(rs.getLong("Customer_AadharNumber"));
					cd.setMobileNumber(rs.getLong("Customer_MobileNumber"));
					cd.setGender(rs.getString("Gender"));
					
					li.add(cd);
				}
				return li;
			}
		
		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public boolean deleteUsingAadhar(long aadharNumber) {
		try {
			PreparedStatement ps=DbConnection.getConnection().prepareStatement(deleteUsingId);
			ps.setLong(1,aadharNumber);
			int result=ps.executeUpdate();
			if(result!=0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean setAccountNumberAndPin(long accountNumber,int pin,long aadharNumber) {
		try {
			PreparedStatement ps=DbConnection.getConnection().prepareStatement(setAccountNumberAndPin);
			ps.setLong(1,accountNumber);
			ps.setInt(2,pin);
			ps.setLong(3, aadharNumber);
			int result=ps.executeUpdate();
			if(result!=0)
				return true;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
