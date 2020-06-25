package com.excilys.formation.computerDataBase.mapper;

import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;

public class ComputerDTOMapper {
	
	public static Computer dtoToComputer (ComputerDTO computer) {
		return new Computer(
			Integer.valueOf(computer.getId()),
			computer.getName(),
			DateMapper.stringToLocalDate(computer.getDiscontinued()),
			DateMapper.stringToLocalDate(computer.getDiscontinued()),
			Integer.valueOf(computer.getCompanyId()));
	}
	
	public static ComputerDTO computerToDTO (Computer computer) {
		return new ComputerDTO(
			Integer.valueOf(computer.getId()).toString(),
			computer.getName(),
			DateMapper.LocalDateToString(computer.getIntroduced()),
			DateMapper.LocalDateToString(computer.getDiscontinued()),
			Integer.valueOf(computer.getCompagnyId()).toString());
	}
}
