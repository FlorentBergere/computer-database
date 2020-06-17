package com.excilys.formation.computerDataBase.model;

import java.sql.Date;

public class Computer {
	
	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private int compagnyId;
	
	public Computer(int id, String name, Date introduced, Date discontinued, int compagnyId){
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.compagnyId = compagnyId;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	
	public Date getIntroduced() {
		return introduced;
	}


	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	

	public Date getDiscontinued() {
		return discontinued;
	}


	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}


	
	public int getCompagnyId() {
		return compagnyId;
	}


	public void setCompagnyId(int compagnyId) {
		this.compagnyId = compagnyId;
	}
	
	@Override
	public String toString() {
		return 	"Id : " + String.valueOf(this.id) + 
				" Company : " + this.name +
				"introduced : " + String.valueOf(this.introduced) +
				"discontinued : " + String.valueOf(this.discontinued) +
				"compagny ID : " + String.valueOf(this.compagnyId);
	}

}