package com.excilys.formation.computerDataBase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import com.excilys.formation.computerDataBase.collection.ComputerCollection;
import com.excilys.formation.computerDataBase.model.Computer;

public class ComputerDAO {
	private static String URL = "jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC";
    private static String LOGIN = "admincdb";
    private static String PASSWORD = "azerty1234";
    private final static String QUERY_FIND_COMPUTER = "SELECT * FROM computer";
    
    public ComputerCollection findComputer () throws SQLException{
	    Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
		Statement stmt = con.createStatement();
		ResultSet rset = stmt.executeQuery(QUERY_FIND_COMPUTER);
		
		ComputerCollection computers = new ComputerCollection();
		
		while(rset.next()) {
			Computer computer= new Computer(	rset.getInt("id"),
												rset.getString("name"),
												rset.getDate("introduced"),
												rset.getDate("discontinued"),
												rset.getInt("company_id") );
			computers.addComputer(computer);
		}
		
		return computers;
    }
}
