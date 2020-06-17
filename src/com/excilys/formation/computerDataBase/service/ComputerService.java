package com.excilys.formation.computerDataBase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

public class ComputerService {
	ComputerDAO computerDAO;
	
	public ComputerService () {
		this.computerDAO = new ComputerDAO();
	}
	
	public List<String> listComputer () {
		ArrayList<String> result = new ArrayList<String>();
		List<Computer> computerCollection = computerDAO.getComputerCollection();
		for(Computer c : computerCollection) {
			result.add(c.toString());
			//TODO utiliser un mapper plutot que le ToString;
		}
		
		return result;
	}
}
