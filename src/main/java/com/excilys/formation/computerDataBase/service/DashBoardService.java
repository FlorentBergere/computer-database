package com.excilys.formation.computerDataBase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.mapper.ComputerDTOMapper;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

public class DashBoardService {
	private ComputerDAO computerDAO;
	
	public DashBoardService () {
		this.computerDAO = new ComputerDAO();
	}
	
	public List<ComputerDTO> findAll (){
		ArrayList<ComputerDTO> result = new ArrayList<ComputerDTO>();
		List<Computer> computerCollection = computerDAO.findAll();
		for(Computer c : computerCollection) {
			result.add(ComputerDTOMapper.computerToDTO(c));
		}
		return result;
	}
}
