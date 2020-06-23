package com.excilys.formation.computerDataBase.mapper;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.excilys.formation.computerDataBase.model.Computer;

public class ComputerMapperTest {

	private ResultSet rset = Mockito.mock(ResultSet.class);
	
	private final static int ID = 42;
	private final static String NAME = "le nom d'un ordinateur";
	private final static Date INTRODUCED = Date.valueOf("2010-10-11");
	private final static Date DISCONTINUED = Date.valueOf("2011-01-25");
	private final static int COMPANY_ID = 3;
	

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
	}
	@Test
	public void testrsetToComputer() {
		try {
			Mockito.when(rset.getInt("id")).thenReturn(ID);
			Mockito.when(rset.getString("name")).thenReturn(NAME);
			Mockito.when(rset.getDate("introduced")).thenReturn(INTRODUCED);
			Mockito.when(rset.getDate("discontinued")).thenReturn(DISCONTINUED);
			Mockito.when(rset.getInt("company_id")).thenReturn(COMPANY_ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Computer computer = new Computer(ID,NAME,DateMapper.sqlDateToLocalDate(INTRODUCED),DateMapper.sqlDateToLocalDate(DISCONTINUED),COMPANY_ID);
		
		try {
			assertEquals(computer,ComputerMapper.rsetToComputer(rset));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
