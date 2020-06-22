package com.excilys.formation.computerDataBase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.computerDataBase.model.Company;

public class CompanyMapper {
	public static Company rsetToCompany (ResultSet rset) throws SQLException {
    	return new Company(	rset.getInt("id"),
							rset.getString("name"));
	}
}
