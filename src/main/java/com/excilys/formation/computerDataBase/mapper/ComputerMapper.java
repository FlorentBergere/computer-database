package com.excilys.formation.computerDataBase.mapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.formation.computerDataBase.model.Computer;

public class ComputerMapper {

	

	
	public static void toStatementAdd(PreparedStatement stmt, Computer c) throws SQLException {
			stmt.setString(1, c.getName());
	    	stmt.setDate(2, DateMapper.localDateTosqlDate(c.getIntroduced()));
	    	stmt.setDate(3, DateMapper.localDateTosqlDate(c.getDiscontinued()));
	    	stmt.setInt(4, c.getCompagnyId());
		
	}
	
	
	public static Computer rsetToComputer (ResultSet rset) throws SQLException {
    	return new Computer(rset.getInt("id"),
							rset.getString("name"),
							DateMapper.sqlDateToLocalDate(rset.getDate("introduced")),
							DateMapper.sqlDateToLocalDate(rset.getDate("discontinued")),
							rset.getInt("company_id") );
	}
}
