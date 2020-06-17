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
import com.mysql.cj.protocol.Resultset;

public class ComputerDAO {
	Connection con = new Connection();
    private final static String QUERY_FIND_COMPUTER = "SELECT * FROM computer";
    private final static String QUERY_FINDBYID = "SELECT  * FROM computer where id = ?";
    
    
    
    public List<Computer> findAll () {
    	ArrayList<Computer> result = new ArrayList<Computer>();
    	try {
    		Statement stmt = con.getConnection().createStatement();
    		ResultSet rset = stmt.executeQuery(QUERY_FIND_COMPUTER);
    		
    		while(rset.next()) {
    			result.add(rsetToComputer(rset));
    		}
    	}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
		
    }
    
    public List<Computer> fingByID (int id) {
    	ArrayList<Computer> result = new ArrayList<Computer>();
    	try {
	    	PreparedStatement stmt = con.getConnection().prepareStatement(QUERY_FINDBYID);
	    	stmt.setInt(1, id);
	    	
	    	ResultSet rset = stmt.executeQuery();
	    	
	    	while (rset.next()) {
	    		result.add(rsetToComputer(rset));
	    	}
	    }catch (Exception e) {
			// TODO: handle exception
		}
    	return result;
    }
    
    public void add (String name, Date introduced, Date discontinued, int compagnyId) {
    
    	String query = "insert into computer (name, introduced, discontinued, company_id)"
    	        + " values (?, ?, ?, ?)";
    	
    	try {
    		PreparedStatement stmt = con.getConnection().prepareStatement(query);
        	stmt.setString(1, name);
        	stmt.setDate(2, introduced);
        	stmt.setDate(3, discontinued);
        	stmt.setInt(4, compagnyId);
        	
        	stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    }
    
    
    private Computer rsetToComputer (ResultSet rset) throws SQLException {
    	return new Computer(rset.getInt("id"),
							rset.getString("name"),
							rset.getDate("introduced"),
							rset.getDate("discontinued"),
							rset.getInt("company_id") );
    }
    
}
