package com.ilp.proj1.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class MainUtility 
{
	
	/*
	 * Initializing the variables
	 * 
	 */
	public static final String driverName="oracle.jdbc.driver.OracleDriver";
	public static final String url="jdbc:oracle:thin:@172.18.65.245:1521:tcsghy";
	public static final String username="g11_05core";
	public static final String password="tcsghy";
	 Connection con=null;
	/*
	 * 
	 * Declaring getConnection method
	 * 
	 */
	
	public Connection getConnection()
	{
		try
		{
			Class.forName(driverName);
			con=DriverManager.getConnection(url,username,password);
		}
		catch(ClassNotFoundException e)
		{
			//System.out.println(e.getStackTrace());
			//System.out.println("1");
		}
		catch(SQLException e)
		{
			//System.out.println(e.getStackTrace());
			//System.out.println("2");
		}
		return con;
		
	}
	
	/*
	 * 
	 * declaring getCloseConnection method
	 * 
	 */
	
	public void closeConnection()
	{
		try
		{
			con.close();		
		}
		catch (Exception e)
		{
			//System.out.println(e.getStackTrace());
			//System.out.println("3");
		}
	}
	
}