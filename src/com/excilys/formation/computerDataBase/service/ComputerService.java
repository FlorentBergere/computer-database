package com.excilys.formation.computerDataBase.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

public class ComputerService {
	ComputerDAO computerDAO;
	
	public ComputerService () {
		this.computerDAO = new ComputerDAO();
	}
	
	public List<String> listAll () {
		ArrayList<String> result = new ArrayList<String>();
		//TODO changer pour que findComputer retourne directement une liste
		List<Computer> computerCollection = computerDAO.findAll();
		for(Computer c : computerCollection) {
			result.add(c.toString());
			
			//TODO utiliser un mapper plutot que le ToString;
		}
		
		return result;
	}
	
	
	public List<String> getComputerByID (int id) {
		ArrayList<String> result = new ArrayList<String>();
		List<Computer> computerCollection = computerDAO.fingByID(id);
		for(Computer c : computerCollection) {
			result.add(c.toString());
			//TODO utiliser un mapper plutot que le ToString;
		}
		
		return result;
	}
	
	public void add (String name, Date introduced, Date discontinued, int compagnyId) {
		computerDAO.add(name, introduced, discontinued, compagnyId);
		//TODO verifier que les donnees envoyer par l'utilisateur sont correct
	}
	
	public void update (int id, String name, Date introduced, Date discontinued, int compagnyId) {
		computerDAO.update(id, name, introduced, discontinued, compagnyId);
		//TODO verifier que les donnees envoyer par l'utilisateur sont correct
	}
	
	public void delete(int id) {
		computerDAO.delete(id);
	}
}
