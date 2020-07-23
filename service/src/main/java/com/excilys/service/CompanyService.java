package com.excilys.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.model.Company;
import com.excilys.model.Page;
import com.excilys.persistance.CompanyDAO;

@Service
public class CompanyService {
	@Autowired
	private CompanyDAO companyDAO;
	private Page pageCompany;
	
	public CompanyService () {
	}
	
	public List<String> listAll () {
		return companyDAO.findCompany().stream().map(Company::toString).collect(Collectors.toList());
	}
	
	public List<String> findAllByPage () {
		pageCompany = new Page(companyDAO.countEntry());
		return companyDAO.findAllByPage(pageCompany).stream().map(Company::toString).collect(Collectors.toList());
	}
	
	public List<String> nextPage () {
		System.out.println(pageCompany.next());
		return companyDAO.findAllByPage(pageCompany).stream().map(Company::toString).collect(Collectors.toList());
	}
	
	public List<String> previousPage () {
		System.out.println(pageCompany.previous());
		return companyDAO.findAllByPage(pageCompany).stream().map(Company::toString).collect(Collectors.toList());
	}
	
	public void delete (int id) {
		companyDAO.deleteCompany(new Company(id, null));
	}
}
