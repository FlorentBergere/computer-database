package com.excilys.formation.computerDataBase.ui;

import java.sql.SQLException;
import java.util.List;


import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.persistence.CompanyDAO;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

public class CLI {
	public static void main(String[] args) throws SQLException {
		CompanyDAO companyDAO = new CompanyDAO();
		companyDAO.findCompany();
		List<Company> companyCollection = companyDAO.getCompanyCollection();
		
		ComputerDAO computerDAO = new ComputerDAO();
		computerDAO.findComputer();
		List<Computer> computerCollection = computerDAO.getComputerCollection();
		
		for(Company c : companyCollection) {
			System.out.println(c);
		}
		for(Computer c : computerCollection) {
			System.out.println(c);
		}
		
		
	}
}
