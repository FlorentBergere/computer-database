package com.excilys.formation.computerDataBase.model;

import java.time.LocalDate;

public class Computer {
	
	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private int compagnyId;
	
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinued, int compagnyId){
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


	
	public LocalDate getIntroduced() {
		return introduced;
	}


	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	

	public LocalDate getDiscontinued() {
		return discontinued;
	}


	public void setDiscontinued(LocalDate discontinued) {
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
		return String.format("| %3d | Name : %-65s | Intro : %10s | Disco : %10s | Comp_ID %3d",
			this.getId(),
			this.name,
			String.valueOf(this.introduced),
			String.valueOf(this.discontinued),
			this.compagnyId);
		
	}
	
	public boolean equals(Computer c) {
		return	this.id == c.id && 
				this.name.equals(c.name) &&
				this.introduced.equals(c.introduced) &&
				this.discontinued.equals(c.discontinued) &&
				this.compagnyId == c.compagnyId;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash += hash*this.id;
		hash += hash*this.name.hashCode();
		hash += hash*this.introduced.hashCode();
		hash += hash*this.discontinued.hashCode();
		hash += hash*this.getCompagnyId();
		return hash;
	}
}
