package com.excilys.formation.computerDataBase.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.computerDataBase.model.Computer;

public class ComputerMapper {

	

	
	public static void toStatementAdd(PreparedStatement stmt, Computer c) {
		try {
			stmt.setString(1, c.getName());
	    	stmt.setDate(2, c.getIntroduced());
	    	stmt.setDate(3, c.getDiscontinued());
	    	stmt.setInt(4, c.getCompagnyId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	public static Computer rsetToComputer (ResultSet rset) throws SQLException {
    	return new Computer(rset.getInt("id"),
							rset.getString("name"),
							rset.getDate("introduced"),
							rset.getDate("discontinued"),
							rset.getInt("company_id") );
	}
}
