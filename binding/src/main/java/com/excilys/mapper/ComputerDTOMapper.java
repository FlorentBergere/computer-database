package com.excilys.mapper;


import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;

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
			computer.getCompany() != null ? new CompanyDTO(Integer.valueOf(computer.getCompanyId()).toString(), computer.getComanyName()) : null);
	}
	

	
}
