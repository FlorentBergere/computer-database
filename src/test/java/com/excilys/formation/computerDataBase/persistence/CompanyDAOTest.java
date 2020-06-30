package com.excilys.formation.computerDataBase.persistence;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.computerDataBase.service.ConnectionFactory;
import com.mysql.cj.xdevapi.Result;
import com.excilys.formation.computerDataBase.model.Company;

public class CompanyDAOTest {
	private CompanyDAO companyDAO;
	
	private static List<Company> expectedListAll = Arrays.asList(	new Company(1, "Apple Inc."),
																	new Company(2, "Thinking Machines"),
																	new Company(3, "RCA"));
	
	
	@Before
	public void setUp() throws Exception {
		companyDAO = new CompanyDAO();
	}

	@Test
	public void testFinDCompany() {
		List<Company> result = companyDAO.findCompany();

		assertEquals(expectedListAll, result);
	}

	
}
