package com.excilys.formation.computerDataBase.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.Page;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

public class ComputerService {
	private ComputerDAO computerDAO;
	private Page pageComputer;
	
	public ComputerService () {
		this.computerDAO = new ComputerDAO(ConnectionFactory.Mode.PROD);
	}
	
	public List<String> listAll () {
		ArrayList<String> result = new ArrayList<String>();
		List<Computer> computerCollection = computerDAO.findAll();
		for(Computer c : computerCollection) {
			result.add(c.toString());
		}
		
		return result;
	}
	
	
	public List<String> getComputerByID (int id) {
		ArrayList<String> result = new ArrayList<String>();
		List<Computer> computerCollection = computerDAO.fingByID(id);
		for(Computer c : computerCollection) {
			result.add(c.toString());
		}
		
		return result;
	}
	
	public void add (String name, LocalDate introduced, LocalDate discontinued, int compagnyId) {
		computerDAO.add(name, introduced, discontinued, compagnyId);
		//TODO verifier que les donnees envoyer par l'utilisateur sont correct
	}
	
	public void update (int id, String name, LocalDate introduced, LocalDate discontinued, int compagnyId) {
		computerDAO.update(id, name, introduced, discontinued, compagnyId);
		//TODO verifier que les donnees envoyer par l'utilisateur sont correct
	}
	
	public void delete(int id) {
		computerDAO.delete(id);
	}
	
	public List<String> findAllByPage () {
		ArrayList<String> result = new ArrayList<String>();
		pageComputer = new Page(computerDAO.countEntry());
		List<Computer> computerCollection = computerDAO.findAllByPage(pageComputer.getOffset(), pageComputer.getNbEntryPerPage());
		for(Computer c : computerCollection) {
			result.add(c.toString());
		}
		return result;
	}
	public List<String> nextPage () {
		ArrayList<String> result = new ArrayList<String>();
		System.out.println(pageComputer.next());
		List<Computer> computerCollection = computerDAO.findAllByPage(pageComputer.getOffset(), pageComputer.getNbEntryPerPage());
		for(Computer c : computerCollection) {
			result.add(c.toString());
		}
		return result;
	}
	
	public List<String> previousPage () {
		ArrayList<String> result = new ArrayList<String>();
		System.out.println(pageComputer.previous());
		List<Computer> computerCollection = computerDAO.findAllByPage(pageComputer.getOffset(), pageComputer.getNbEntryPerPage());
		for(Computer c : computerCollection) {
			result.add(c.toString());
		}
		return result;
	}
}
