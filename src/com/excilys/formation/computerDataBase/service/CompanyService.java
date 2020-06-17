package com.excilys.formation.computerDataBase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.persistence.CompanyDAO;

public class CompanyService {
	CompanyDAO companyDAO;
	
	public CompanyService () {
		this.companyDAO = new CompanyDAO();
	}
	
	public List<String> listAll () {
		ArrayList<String> result = new ArrayList<String>();
		//TODO faire en sorte que findcompagny renvoi directement une liste
		companyDAO.findCompany();
		List<Company> companyCollection = companyDAO.getCompanyCollection();
		for(Company c : companyCollection) {
			result.add(c.toString());
		}
		
		return result;
	}
}
