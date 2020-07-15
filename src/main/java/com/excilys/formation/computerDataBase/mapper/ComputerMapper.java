package com.excilys.formation.computerDataBase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;

@Component
public class ComputerMapper implements RowMapper<Computer> {
	public static Computer rsetToComputer (ResultSet rset) throws SQLException {
    	return new Computer(rset.getInt("id"),
							rset.getString("computerName"),
							DateMapper.sqlDateToLocalDate(rset.getDate("introduced")),
							DateMapper.sqlDateToLocalDate(rset.getDate("discontinued")),
							new Company(rset.getInt("company_id"),rset.getString("company_name")));
	}
	
	public static String parseAtribute (String atribute){
		String result = null;
		
		if (atribute == null) {
			result = Computer.atributes.ID.getAtribute();
		}
		else {
			switch (atribute) {
				case "computerName":
					result = Computer.atributes.NAME.getAtribute();
					break;
		
				default:
					result = Computer.atributes.ID.getAtribute();
					break;
			}	
		}

		return result;
	}

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return ComputerMapper.rsetToComputer(rs);
	}
}
