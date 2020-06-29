package com.excilys.formation.computerDataBase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.mapper.ComputerDTOMapper;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.Page;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

public class DashBoardService {
	private ComputerDAO computerDAO;
	private Page pageComputer;
	
	public DashBoardService () {
		this.computerDAO = new ComputerDAO(ConnectionFactory.Mode.PROD);
	}
	
	public List<ComputerDTO> findAll (){
		ArrayList<ComputerDTO> result = new ArrayList<ComputerDTO>();
		List<Computer> computerCollection = computerDAO.findAll();
		for(Computer c : computerCollection) {
			result.add(ComputerDTOMapper.computerToDTO(c));
		}
		return result;
	}
	
	public int countComputer() {
		return computerDAO.countEntry();
	}
	
	public List<ComputerDTO> findAllByPage(int nbEntryPerPage,int pageNumber){
		List<ComputerDTO> result = new ArrayList<ComputerDTO>();
		pageComputer = new Page(computerDAO.countEntry(),nbEntryPerPage);
		pageComputer.goTo(pageNumber);
		List<Computer> computerCollection = computerDAO.findAllByPage(pageComputer.getOffset(), pageComputer.getNbEntryPerPage());
		for(Computer c :computerCollection) {
			result.add(ComputerDTOMapper.computerToDTO(c));
		}
		return result;
	}
	
	public int getNumberMaxPage() {
		return this.pageComputer.getNbPage();
	}
	
	public List<Integer> getListPage (){
		return this.pageComputer.getListPage();
	}

}
