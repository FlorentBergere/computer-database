package com.excilys.formation.computerDataBase.mapper;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.DTO.CompanyDTO;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;

public class ComputerDTOMapper {
	
	public static Computer dtoToComputer (ComputerDTO computerDTO) {
		return new Computer(
			Integer.valueOf(computerDTO.getId()),
			computerDTO.getName(),
			DateMapper.stringToLocalDate(computerDTO.getIntroduced()),
			DateMapper.stringToLocalDate(computerDTO.getDiscontinued()),
			new Company(Integer.valueOf(computerDTO.getCompanyId()),computerDTO.getCompanyName()));
	}
	
	public static ComputerDTO computerToDTO (Computer computer) {
		return new ComputerDTO(
			Integer.valueOf(computer.getId()).toString(),
			computer.getName(),
			DateMapper.LocalDateToString(computer.getIntroduced()),
			DateMapper.LocalDateToString(computer.getDiscontinued()),
			new CompanyDTO(Integer.valueOf(computer.getId()).toString(), computer.getComanyName()));
	}
}
