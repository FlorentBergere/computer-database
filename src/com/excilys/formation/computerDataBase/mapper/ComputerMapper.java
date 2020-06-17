package com.excilys.formation.computerDataBase.mapper;

import com.excilys.formation.computerDataBase.model.Computer;

public class ComputerMapper {
	public static String printAll(Computer c) {
		return String.format("| %3d | Name : %-65s | Intro : %10s | Disco : %10s | Comp_ID %3d", c.getId(),
																								c.getName(),
																								String.valueOf(c.getIntroduced()),
																								String.valueOf(c.getDiscontinued()),
																								c.getCompagnyId());
	}
}
