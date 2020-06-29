package com.excilys.formation.computerDataBase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Page;
import com.excilys.formation.computerDataBase.persistence.CompanyDAO;

public class CompanyService {
	CompanyDAO companyDAO;
	private Page pageCompany;
	
	public CompanyService () {
		this.companyDAO = new CompanyDAO(ConnectionFactory.Mode.PROD);
	}
	
	public List<String> listAll () {
		ArrayList<String> result = new ArrayList<String>();
		List<Company> companyCollection = companyDAO.findCompany();
		for(Company c : companyCollection) {
			result.add(c.toString());
		}
		
		return result;
	}
	
	public List<String> findAllByPage () {
		ArrayList<String> result = new ArrayList<String>();
		pageCompany = new Page(companyDAO.countEntry());
		List<Company> companyCollection = companyDAO.findAllByPage(pageCompany.getOffset(), pageCompany.getNbEntryPerPage());
		for(Company c : companyCollection) {
			result.add(c.toString());
		}
		return result;
	}
	public List<String> nextPage () {
		ArrayList<String> result = new ArrayList<String>();
		System.out.println(pageCompany.next());
		List<Company> companyCollection = companyDAO.findAllByPage(pageCompany.getOffset(), pageCompany.getNbEntryPerPage());
		for(Company c : companyCollection) {
			result.add(c.toString());
		}
		return result;
	}
	
	public List<String> previousPage () {
		ArrayList<String> result = new ArrayList<String>();
		System.out.println(pageCompany.previous());
		List<Company> companyCollection = companyDAO.findAllByPage(pageCompany.getOffset(), pageCompany.getNbEntryPerPage());
		for(Company c : companyCollection) {
			result.add(c.toString());
		}
		return result;
	}
}
