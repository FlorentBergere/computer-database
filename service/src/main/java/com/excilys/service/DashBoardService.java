package com.excilys.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Page;
import com.excilys.persistence.ComputerDAO;

@Service
public class DashBoardService {
	@Autowired
	private ComputerDAO computerDAO;
	
	private Page pageComputer;
	
	
	public DashBoardService () {
	}
	
	public Page initPage() {
		if ( pageComputer == null) {
			this.pageComputer = new Page(countComputer());
		}
		return this.pageComputer;
	}

	public List<ComputerDTO> findAll (){
		return computerDAO.findAll().stream().map(computer -> ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
	}
	
	public int countComputer() {
		return computerDAO.countEntry();
	}
	
	public List<ComputerDTO> findAllByPage(){
		return computerDAO.findAllByPage(pageComputer).stream().map(computer -> ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
	}
	
//	public List<ComputerDTO> findAllByPage(int nbEntryPerPage,int pageNumber){
//		pageComputer = new Page(computerDAO.countEntry(),nbEntryPerPage);
//		pageComputer.goTo(pageNumber);
//		return computerDAO.findAllByPage(pageComputer).stream().map(computer -> ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
//	}
//	
//	public List<ComputerDTO> findAllByPage(int nbEntryPerPage,int pageNumber, Computer.atributes atribute){
//		pageComputer = new Page(computerDAO.countEntry(),nbEntryPerPage);
//		pageComputer.goTo(pageNumber);
//		pageComputer.setAttributeToOrder(atribute.getAtribute());
//		return computerDAO.findAllByPage(pageComputer).stream().map(computer -> ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
//	}
//	
//	public List<ComputerDTO> findAllByPage(int nbEntryPerPage,int pageNumber, String search){
//		pageComputer = new Page(computerDAO.countEntry(),nbEntryPerPage);
//		pageComputer.goTo(pageNumber);
//		pageComputer.setSearch(search);
//		return computerDAO.findAllByPage(pageComputer).stream().map(computer -> ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
//	}
//	
//	public List<ComputerDTO> findAllByPage(int nbEntryPerPage,int pageNumber, String search, Computer.atributes atribute){
//		pageComputer = new Page(computerDAO.countEntry(),nbEntryPerPage);
//		pageComputer.goTo(pageNumber);
//		pageComputer.setAttributeToOrder(atribute.getAtribute());
//		pageComputer.setSearch(search);
//		return computerDAO.findAllByPage(pageComputer).stream().map(computer -> ComputerDTOMapper.computerToDTO(computer)).collect(Collectors.toList());
//	}
	
	
//	public int getNumberMaxPage() {
//		return this.pageComputer.getNbPage();
//	}
//	
//	public List<Integer> getListPage (){
//		return this.pageComputer.getListPage();
//	}
	
	public void setPageLength (int pageLength) {
		pageComputer.setPageLength(pageLength);
	}
	
	public void goTo (int pageNumber) {
		pageComputer.goTo(pageNumber);
	}
	
	public void setSearch (String search) {
		pageComputer.setSearch(search);
	}
	
	public void setAttributeToOrder (String atribute) {
		pageComputer.setAttributeToOrder(atribute);
	}
	
	
	
	public void delete(String idsToDelete) {
		String toDelete [] = idsToDelete.split(",");
		for(String id : toDelete) {
			computerDAO.delete(Integer.valueOf(id));
		}
	}

}
