package com.excilys.formation.computerDataBase.persistence;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.computerDataBase.service.ConnectionFactory;
import com.mysql.cj.xdevapi.Result;
import com.excilys.formation.computerDataBase.model.Company;

public class CompanyDAOTest {
	private CompanyDAO companyDAO;
	
	private List<Company> expectedListAll;
	
	
	@Before
	public void setUp() throws Exception {
		companyDAO = new CompanyDAO(ConnectionFactory.Mode.TEST);
		
		expectedListAll = new ArrayList<Company>();
		expectedListAll.add(new Company(1, "Apple Inc."));
		expectedListAll.add(new Company(2, "Thinking Machines"));
		expectedListAll.add(new Company(3, "RCA"));
		
	}

	@Test
	public void test() {
		List<Company> result = new ArrayList<Company>();
		result = companyDAO.findCompany();
		assertEquals(expectedListAll, result);
	}

	
	
}
