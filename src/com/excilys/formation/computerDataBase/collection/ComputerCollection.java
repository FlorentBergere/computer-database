package com.excilys.formation.computerDataBase.collection;

import java.util.ArrayList;
import java.util.List;
import com.excilys.formation.computerDataBase.model.Computer;

public class ComputerCollection {
	private List<Computer> computers;

	public ComputerCollection (){
		this.computers = new ArrayList<Computer>();
	}
	
	public void addComputer(Computer c){
		this.computers.add(c);
	}
}
