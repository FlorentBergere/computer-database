package com.excilys.formation.computerDataBase.ui;

import java.sql.SQLException;

import com.excilys.formation.computerDataBase.collection.CompanyCollection;
import com.excilys.formation.computerDataBase.collection.ComputerCollection;
import com.excilys.formation.computerDataBase.persistence.CompanyDAO;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

public class CLI {
	public static void main(String[] args) throws SQLException {
		CompanyDAO companyDAO = new CompanyDAO();
		CompanyCollection companys = companyDAO.findCompany();
		
		ComputerDAO computerDAO = new ComputerDAO();
		ComputerCollection computers = computerDAO.findComputer();
		
		System.out.println(companys);
		System.out.println(computers);
		
	}
}
