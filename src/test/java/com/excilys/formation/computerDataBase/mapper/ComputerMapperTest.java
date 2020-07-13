package com.excilys.formation.computerDataBase.mapper;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;

public class ComputerMapperTest {

	private ResultSet rset = Mockito.mock(ResultSet.class);
	
	private final static int ID = 42;
	private final static String NAME = "le nom d'un ordinateur";
	private final static Date INTRODUCED = Date.valueOf("2010-10-11");
	private final static Date DISCONTINUED = Date.valueOf("2011-01-25");
	private final static Company COMPANY = new Company(999,"lolilol");
	

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
	}
	@Test
	public void testrsetToComputer() throws SQLException {

		Mockito.when(rset.getInt("id")).thenReturn(ID);
		Mockito.when(rset.getString("computerName")).thenReturn(NAME);
		Mockito.when(rset.getDate("introduced")).thenReturn(INTRODUCED);
		Mockito.when(rset.getDate("discontinued")).thenReturn(DISCONTINUED);
		Mockito.when(rset.getInt("company_id")).thenReturn(COMPANY.getId());
		Mockito.when(rset.getString("company_name")).thenReturn(COMPANY.getName());

		
		Computer computer = new Computer(ID,NAME,DateMapper.sqlDateToLocalDate(INTRODUCED),DateMapper.sqlDateToLocalDate(DISCONTINUED),COMPANY);
		

		assertEquals(computer,ComputerMapper.rsetToComputer(rset));
	}

}
