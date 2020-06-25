package com.excilys.formation.computerDataBase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;

public class ComputerMapper {
	public static Computer rsetToComputer (ResultSet rset) throws SQLException {
    	return new Computer(rset.getInt("id"),
							rset.getString("name"),
							DateMapper.sqlDateToLocalDate(rset.getDate("introduced")),
							DateMapper.sqlDateToLocalDate(rset.getDate("discontinued")),
							new Company(rset.getInt("company_id"),rset.getString("company_name")));
	}
}
