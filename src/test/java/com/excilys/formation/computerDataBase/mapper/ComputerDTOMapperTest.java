package com.excilys.formation.computerDataBase.mapper;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.DTO.CompanyDTO;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;

public class ComputerDTOMapperTest {
	
	
	private final static String idString = "42";
	private final static String name = "la reponse a la vie l'univer et le reste";
	private final static String introducedString = "1998-09-28";
	private final static String discontinuedString = "1999-12-13";
	private final static String companyIdString = "53";
	private final static String companyName = "La compagny cybernetique de Cyrius";
	
	private final static int id = Integer.valueOf(idString);	
	private final static LocalDate introduced = DateMapper.stringToLocalDate(introducedString);
	private final static LocalDate discontinued = DateMapper.stringToLocalDate(discontinuedString);
	private final static int companyId = Integer.valueOf(companyIdString);

	
	private final static Computer computer = new Computer(id,name,introduced,discontinued,new Company(companyId,companyName));
	private final static ComputerDTO computerDTO = new ComputerDTO(idString, name, introducedString, discontinuedString, new CompanyDTO(companyIdString, companyName));


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDTOToComputer() {
		assertEquals(computer, ComputerDTOMapper.dtoToComputer(computerDTO));
	}
	
	@Test
	public void testComputerToDTO() {
		assertEquals(computerDTO, ComputerDTOMapper.computerToDTO(computer));
	}
	

}
