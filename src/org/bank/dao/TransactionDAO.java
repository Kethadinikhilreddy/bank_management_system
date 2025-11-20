package org.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.bank.dto.TransactionDetails;

public class TransactionDAO {
	
	private static final String insert_transaction_details="insert into transaction_details(TransactionDate, TransactionTime, TransactionType, TransactionAmount, Balance, AccountNumber) values(?,?,?,?,?,?)";
	
	private static final String selectByUsing_AccountNumber="select * from transaction_details where AccountNumber=?";
	
	
	public boolean insertTransactionDetails(TransactionDetails transactionDetails) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/bank_management_system?user=root&password=root");
			PreparedStatement ps=connection.prepareStatement(insert_transaction_details);
			ps.setDate(1,Date.valueOf(transactionDetails.getDate()));
			ps.setTime(2,Time.valueOf(transactionDetails.getTime()));
			ps.setString(3,transactionDetails.getTransactionType());
			ps.setDouble(4,transactionDetails.getTransactionAmount());
			ps.setDouble(5,transactionDetails.getBalanceAmount());
			ps.setLong(6,transactionDetails.getAccountNumber());
			
			int result=ps.executeUpdate();
			if(result!=0) {
				return true;
			}
				
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	List<TransactionDetails> al=new ArrayList<TransactionDetails>();
	public  List<TransactionDetails> getTransactionDetailsByUsingAccountNumber(long accountNumber) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/bank_management_system?user=root&password=root");
			PreparedStatement ps=connection.prepareStatement(selectByUsing_AccountNumber);
			ps.setLong(1, accountNumber);
			
			ResultSet rs= ps.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					TransactionDetails td=new TransactionDetails();
					td.setTransactionId(rs.getInt("TransactionId"));
					td.setTransactionType(rs.getString("TransactionType"));
					td.setDate(rs.getDate("TransactionDate").toLocalDate());
					td.setTime(rs.getTime("TransactionTime").toLocalTime());
					td.setTransactionAmount(rs.getDouble("TransactionAmount"));
				    td.setBalanceAmount(rs.getDouble("Balance"));
				    
				    al.add(td);
				}
				return al;
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
