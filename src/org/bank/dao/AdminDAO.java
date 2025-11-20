package org.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
	
	private static final String admin_login="select * from admin_details where AdminEMailId=? and AdminPassword=?";
	
	public boolean  selectAdminDetailsUsingEmailAndPassword(String email,String password) {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/bank_management_system?user=root&password=root");
			PreparedStatement ps=connection.prepareStatement(admin_login);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	
	}

}
