package com.des.dbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbManager
{
	private final String	USER_NAME	= "root";
	private final String	PASSWORD	= "root";
	private final String	DRIVER		= "com.mysql.jdbc.Driver";
	private final String	HOST		= "localhost:3306";
	private final String	DBNAME		= "test";
	private final String	URL			= "jdbc:mysql://" + HOST + "/" + DBNAME;

	// method for establishing connection
	public Connection connection() throws ClassNotFoundException, SQLException
	{
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
	}

	// method for executing insert , update , delete query
	public Integer executeUpdate(String query) throws ClassNotFoundException, SQLException
	{
		Integer result = null;
		Connection connection = null;
		try
		{
			connection = connection();
			if (connection != null)
			{
				PreparedStatement ps = connection.prepareStatement(query);
				result = ps.executeUpdate();
			}
		}
		finally
		{
			if (connection != null)
				connection.close();
		}
		return result;
	}

	// method for executing select query
	public List<Map<String, Object>> executeQuery(String query) throws SQLException, ClassNotFoundException
	{
		ResultSet resultSet = null;
		Connection connection = null;
		List<Map<String, Object>> customers = new ArrayList<Map<String, Object>>();
		try
		{
			connection = connection();
			if (connection != null)
			{
				PreparedStatement ps = connection.prepareStatement(query);
				resultSet = ps.executeQuery();
				Map<String, Object> customerMap = null;
				while (resultSet.next())
				{
					customerMap = new HashMap<String, Object>();
					customerMap.put("id", resultSet.getInt("id"));
					customerMap.put("fullName", resultSet.getString("fullName"));
					customerMap.put("gender", resultSet.getString("gender"));
					customerMap.put("address", resultSet.getString("address"));
					customerMap.put("landMark", resultSet.getString("landMark"));
					customerMap.put("pinCode", resultSet.getString("pinCode"));
					customerMap.put("contactNo", resultSet.getString("contactNo"));
					customerMap.put("email", resultSet.getString("email"));
					customerMap.put("dob", resultSet.getString("dob"));
					customerMap.put("maritialStatus", resultSet.getString("maritialStatus"));
					customerMap.put("anniversary", resultSet.getString("anniversary"));
					customerMap.put("city", resultSet.getString("city"));

					customers.add(customerMap);
				}
			}
		}		
		finally
		{
			if (connection != null)
				connection.close();
		}
		return customers;
	}

	// method for executing select query
	public boolean isAuthenticated(String userId, String password) throws ClassNotFoundException, SQLException
	{
		ResultSet resultSet = null;
		Connection connection = null;
		final String query = "SELECT * FROM users WHERE userid='" + userId + "' AND password = '" + password + "'";	
		boolean isAuthenticated = false;
		try
		{
			connection = connection();
			if (connection != null)
			{
				PreparedStatement ps = connection.prepareStatement(query);
				resultSet = ps.executeQuery();
				isAuthenticated = resultSet.next();
			}
		}		
		finally
		{
			if (connection != null)
				connection.close();
		}
		return isAuthenticated;
	}
	
	public boolean isUserExists(String userId) throws ClassNotFoundException, SQLException
	{
		ResultSet resultSet = null;
		Connection connection = null;
		final String query = "SELECT * FROM users WHERE userid='" + userId + "'";	
		boolean isExists = false;
		try
		{
			connection = connection();
			if (connection != null)
			{
				PreparedStatement ps = connection.prepareStatement(query);
				resultSet = ps.executeQuery();
				isExists = resultSet.next();
			}
		}		
		finally
		{
			if (connection != null)
				connection.close();
		}
		return isExists;
	}
}