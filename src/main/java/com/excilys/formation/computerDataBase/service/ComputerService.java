package com.excilys.formation.computerDataBase.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.Page;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;


public class ComputerService {
	private ComputerDAO computerDAO;
	private Page pageComputer;
	
	public ComputerService () {
		this.computerDAO = new ComputerDAO();
	}
	
	public List<String> listAll () {
		return computerDAO.findAll().stream().map(Computer::toString).collect(Collectors.toList());
	}
	
	
	public List<String> getComputerByID (int id) {
		return computerDAO.fingByID(id).stream().map(Computer::toString).collect(Collectors.toList());
	}
	
	public void add (String name, LocalDate introduced, LocalDate discontinued, int compagnyId) {
		computerDAO.add(name, introduced, discontinued, compagnyId);
	}
	
	public void update (int id, String name, LocalDate introduced, LocalDate discontinued, int compagnyId) {
		computerDAO.update(id, name, introduced, discontinued, compagnyId);
	}
	
	public void delete(int id) {
		computerDAO.delete(id);
	}
	
	public List<String> findAllByPage () {
		pageComputer = new Page(computerDAO.countEntry());
		return computerDAO.findAllByPage(pageComputer.getOffset(), pageComputer.getNbEntryPerPage()).stream().map(Computer::toString).collect(Collectors.toList());
	}
	
	public List<String> nextPage () {
		pageComputer.next();
		return computerDAO.findAllByPage(pageComputer.getOffset(), pageComputer.getNbEntryPerPage()).stream().map(Computer::toString).collect(Collectors.toList());
	}
	
	public List<String> previousPage () {
		pageComputer.previous();
		return computerDAO.findAllByPage(pageComputer.getOffset(), pageComputer.getNbEntryPerPage()).stream().map(Computer::toString).collect(Collectors.toList());
	}
	
}
