package com.excilys.formation.computerDataBase.testperso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDeMySQLConnector {
    private static String URL = "jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC";
    private static String LOGIN = "admincdb";
    private static String PASSWORD = "azerty1234";
    
    private final static String QUERY_FIND_COMPANY = "SELECT * FROM company";
    private final static String QUERY_FIND_COMPUTER = "SELECT * FROM computer";
    
    public static void main(String[] args) throws SQLException {
    	Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
	        Statement stmt = con.createStatement();
	        ResultSet rset = stmt.executeQuery(QUERY_FIND_COMPANY);
	        
	        while( rset.next() ) {
	        	System.out.printf(" ID : %-3s   Company %-50s\n",rset.getString("id"),rset.getString("name")); 
	        }
	        
	        ResultSet rset2 = stmt.executeQuery(QUERY_FIND_COMPUTER);
	        
	        while( rset2.next() ) {
	        	System.out.printf(" ID : %3s PC : %-65s Introduced : %-10s discontinued %-10s company_id : %-3s \n"	,rset2.getString("id")
	        																										,rset2.getString("name")
															        												,rset2.getString("introduced")											        												,rset2.getString("discontinued")
															        												,rset2.getString("company_id")); 
	        }

	}
}
