package com.excilys.formation.computerDataBase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Page;
import com.excilys.formation.computerDataBase.persistence.CompanyDAO;

public class CompanyService {
	CompanyDAO companyDAO;
	private Page pageCompany;
	
	public CompanyService () {
		this.companyDAO = new CompanyDAO();
	}
	
	public List<String> listAll () {
		return companyDAO.findCompany().stream().map(Company::toString).collect(Collectors.toList());
	}
	
	public List<String> findAllByPage () {
		pageCompany = new Page(companyDAO.countEntry());
		return companyDAO.findAllByPage(pageCompany.getOffset(), pageCompany.getNbEntryPerPage()).stream().map(Company::toString).collect(Collectors.toList());
	}
	
	public List<String> nextPage () {
		System.out.println(pageCompany.next());
		return companyDAO.findAllByPage(pageCompany.getOffset(), pageCompany.getNbEntryPerPage()).stream().map(Company::toString).collect(Collectors.toList());
	}
	
	public List<String> previousPage () {
		System.out.println(pageCompany.previous());
		return companyDAO.findAllByPage(pageCompany.getOffset(), pageCompany.getNbEntryPerPage()).stream().map(Company::toString).collect(Collectors.toList());
	}
	
	public void delete (int id) {
		companyDAO.deleteCompany(new Company(id, null));
	}
}
