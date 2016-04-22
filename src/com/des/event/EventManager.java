package com.des.event;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.des.dbo.DbManager;

public class EventManager
{
	public static void saveCustomer(String fullName, String address, String landMark, String pinCode, String email, String contactNo, String gender, String maritialStatus, String dob, String anniversary,String city) throws ClassNotFoundException, SQLException
	{
		final String query = "INSERT INTO customer (fullName,address,landMark,pinCode,email,contactNo,gender,maritialStatus,dob,anniversary,city) VALUES('"+fullName+"','"+address+"','"+landMark+"','"+pinCode+"','"+email+"','"+contactNo+"','"+gender+"','"+maritialStatus+"','"+dob+"','"+anniversary+"','"+city+"')";
		new DbManager().executeUpdate(query);
	}
	
	public static void updateCustomer(Integer id,String fullName, String address, String landMark, String pinCode, String email, String contactNo, String gender, String maritialStatus, String dob, String anniversary,String city) throws ClassNotFoundException, SQLException
	{
		final String query = "UPDATE customer SET fullName = '"+fullName+"',address = '"+address+"',landMark = '"+landMark+"',pinCode = '"+pinCode+"',email = '"+email+"',contactNo = '"+contactNo+"',gender = '"+gender+"',maritialStatus = '"+maritialStatus+"',dob = '"+dob+"',anniversary = '"+anniversary+"',city = '"+city+"' WHERE id="+id;
		new DbManager().executeUpdate(query);
	}
	
	public static void deleteCustomer(Integer id) throws ClassNotFoundException, SQLException
	{
		final String query = "DELETE FROM customer WHERE id="+id;
		new DbManager().executeUpdate(query);
	}
	
	public static List<Map<String,Object>> getAllCustomerList() throws ClassNotFoundException, SQLException
	{
		final String query = "SELECT * FROM customer";
		return new DbManager().executeQuery(query);
	}
	
	public static List<Map<String,Object>> getCustomer(Integer id) throws ClassNotFoundException, SQLException
	{
		final String query = "SELECT * FROM customer where id = "+id;
		return new DbManager().executeQuery(query);
	}
	
	public static void saveUser(String userId, String password, String role) throws ClassNotFoundException, SQLException
	{
		final String query = "INSERT INTO users (userid,password,role) VALUES('"+userId+"','"+password+"','"+role+"')";
		new DbManager().executeUpdate(query);
	}
	
	public static boolean authenticateUser(String userId,String password) throws ClassNotFoundException, SQLException
	{		
		return new DbManager().isAuthenticated(userId, password);
	}
	
	public static boolean isUserExists(String userId) throws ClassNotFoundException, SQLException
	{		
		return new DbManager().isUserExists(userId);
	}
	public static void changePassword(String userId, String password) throws ClassNotFoundException, SQLException
	{
		final String query = "UPDATE users SET password = '"+password+"' WHERE userid = '"+userId+"'" ;
		new DbManager().executeUpdate(query);
	}
}