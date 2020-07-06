package com.excilys.formation.computerDataBase.mapper;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.DTO.CompanyDTO;

public class CompanyDTOMapperTest {
	private static final int idInt = 42;
	private static final String idString = "42";
	private static final String name = "la reponse a la vie l'univers et le reste";
	private static final CompanyDTO companyDTO = new CompanyDTO(idString, name);
	private static final Company company = new Company(idInt, name);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDTOToCompany() {
		assertEquals(company, CompanyDTOMapper.dtoToCompany(companyDTO));
	}
	
	@Test
	public void testCompanyToDTO() {
		assertEquals(companyDTO, CompanyDTOMapper.CompanyToDTO(company));
	}

}
