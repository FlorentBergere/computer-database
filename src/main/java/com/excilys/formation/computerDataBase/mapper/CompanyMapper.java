package com.excilys.formation.computerDataBase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.formation.computerDataBase.model.Company;

@Component
public class CompanyMapper implements RowMapper<Company>{
	public static Company rsetToCompany (ResultSet rset) throws SQLException {
    	return new Company(	rset.getInt("id"),
							rset.getString("name"));
	}

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		return CompanyMapper.rsetToCompany(rs);
	}
}
