package com.excilys.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;

@Service
public class ComputerService {
	@Autowired
	private ComputerDAO computerDAO;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	private Page pageComputer;
	
	public ComputerService () {
	}
	
	public List<String> listAll () {
		return computerDAO.findAll().stream().map(Computer::toString).collect(Collectors.toList());
	}
	
	
	public List<String> getComputerByID (int id) {
		return computerDAO.fingByID(id).stream().map(Computer::toString).collect(Collectors.toList());
	}
	
	public ComputerDTO findById(int id){
		ComputerDTO computerDTO = null;
		computerDTO = ComputerDTOMapper.computerToDTO(computerDAO.fingByID(id).get(0));
		return computerDTO;
	}
	
	public void add (String name, LocalDate introduced, LocalDate discontinued, int compagnyId) {
		Computer computer = new Computer(1,name,introduced,discontinued,companyDAO.fingByID(compagnyId).get(0));
		computerDAO.add(computer);
	}
	
	public void update (int id, String name, LocalDate introduced, LocalDate discontinued, int compagnyId) {
		Computer computer = computerDAO.fingByID(id).get(0);
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setCompany(companyDAO.fingByID(compagnyId).get(0));
		computerDAO.update(computer);
	}
	
	public void delete(int id) {
		computerDAO.delete(id);
	}
	
	public List<String> findAllByPage () {
		pageComputer = new Page(computerDAO.countEntry());
		return computerDAO.findAllByPage(pageComputer).stream().map(Computer::toString).collect(Collectors.toList());
	}
	
	public List<String> nextPage () {
		pageComputer.next();
		return computerDAO.findAllByPage(pageComputer).stream().map(Computer::toString).collect(Collectors.toList());
	}
	
	public List<String> previousPage () {
		pageComputer.previous();
		return computerDAO.findAllByPage(pageComputer).stream().map(Computer::toString).collect(Collectors.toList());
	}
	
	
	
}
