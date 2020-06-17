package com.excilys.formation.computerDataBase.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.service.Connection;

public class ComputerDAO {
	Connection con = new Connection();
    private final static String QUERY_FIND_COMPUTER = "SELECT * FROM computer";
    
    private static List<Computer> computerCollection;
    
    public ComputerDAO() {
    	computerCollection = new ArrayList<Computer>();
    }
    
    public void findComputer () throws SQLException{
		Statement stmt = con.getConnection().createStatement();
		ResultSet rset = stmt.executeQuery(QUERY_FIND_COMPUTER);
		
		while(rset.next()) {
			Computer computer= new Computer(	rset.getInt("id"),
												rset.getString("name"),
												rset.getDate("introduced"),
												rset.getDate("discontinued"),
												rset.getInt("company_id") );
			computerCollection.add(computer);
		}
		
		
    }
    
    public void addComputer (String name, Date introduced, Date discontinued, int compagnyId) throws SQLException {
    
    	String query = "insert into computer (name, introduced, discontinued, company_id)"
    	        + " values (?, ?, ?, ?)";
    	PreparedStatement stmt = con.getConnection().prepareStatement(query);
    	stmt.setString(1, name);
    	stmt.setDate(2, introduced);
    	stmt.setDate(3, discontinued);
    	stmt.setInt(4, compagnyId);
    	
    	stmt.execute();
    	
    }
    
    public List<Computer> getComputerCollection (){
    	return ComputerDAO.computerCollection;
    }
    
    
}
