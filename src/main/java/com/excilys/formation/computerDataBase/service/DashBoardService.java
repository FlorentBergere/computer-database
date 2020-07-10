package com.excilys.formation.computerDataBase.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.computerDataBase.mapper.ComputerDTOMapper;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.Page;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

@Service
public class DashBoardService {
	@Autowired
	private ComputerDAO computerDAO;
	private Page pageComputer;
	
	public DashBoardService () {
	}
	
	public List<ComputerDTO> findAll (){
		return computerDAO.findAll().stream().map(computer -> ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
	}
	
	public int countComputer() {
		return computerDAO.countEntry();
	}
	
	public List<ComputerDTO> findAllByPage(int nbEntryPerPage,int pageNumber){
		pageComputer = new Page(computerDAO.countEntry(),nbEntryPerPage);
		pageComputer.goTo(pageNumber);
		return computerDAO.findAllByPage(pageComputer).stream().map(computer -> ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
	}
	
	public List<ComputerDTO> findAllByPage(int nbEntryPerPage,int pageNumber, Computer.atributes atribute){
		pageComputer = new Page(computerDAO.countEntry(),nbEntryPerPage);
		pageComputer.goTo(pageNumber);
		pageComputer.setAttributeToOrder(atribute.getAtribute());
		return computerDAO.findAllByPage(pageComputer).stream().map(computer -> ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
	}
	
	public List<ComputerDTO> findAllByPage(int nbEntryPerPage,int pageNumber, String search){
		pageComputer = new Page(computerDAO.countEntry(),nbEntryPerPage);
		pageComputer.goTo(pageNumber);
		pageComputer.setSearch(search);
		return computerDAO.findAllByPage(pageComputer).stream().map(computer -> ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
	}
	
	public List<ComputerDTO> findAllByPage(int nbEntryPerPage,int pageNumber, String search, Computer.atributes atribute){
		pageComputer = new Page(computerDAO.countEntry(),nbEntryPerPage);
		pageComputer.goTo(pageNumber);
		pageComputer.setAttributeToOrder(atribute.getAtribute());
		pageComputer.setSearch(search);
		return computerDAO.findAllByPage(pageComputer).stream().map(computer -> ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
	}
	
	
	public int getNumberMaxPage() {
		return this.pageComputer.getNbPage();
	}
	
	public List<Integer> getListPage (){
		return this.pageComputer.getListPage();
	}
	
	public void delete(String idsToDelete) {
		String toDelete [] = idsToDelete.split(",");
		for(String id : toDelete) {
			computerDAO.delete(Integer.valueOf(id));
		}
	}

}
