package com.excilys.formation.computerDataBase.persistence;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.service.ConnectionFactory;
import com.excilys.formation.computerDataBase.mapper.DateMapper;

public class ComputerDAOTest {
	private static ComputerDAO computerDAO;
	
	@Before
	public void setUp() throws Exception {
		ConnectionFactory.resetFactory();
		computerDAO = new ComputerDAO();
	}

	@Test
	public void testAdd() {
		int id = 6;
		String name = "computerName";
		LocalDate introduced = DateMapper.stringToLocalDate("1999-12-01");
		LocalDate discontinued = DateMapper.stringToLocalDate("2014-09-07");
		Company company = new Company(3,"RCA");
		
		
		Computer computer = new Computer(6,name,introduced,discontinued,new Company(3,"RCA"));
		computerDAO.add(name, introduced, discontinued, company.getId());
		
		Computer result = computerDAO.fingByID(id).get(0);
		assertEquals(computer, result);
	}
	
	@Test
	public void testFindByIdWhenNotPresent() {
		int id = 6;
		List<Computer> result = computerDAO.fingByID(id);
		assertTrue(result.isEmpty());
	}

}
