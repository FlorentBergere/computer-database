package com.excilys.formation.computerDataBase.model;

public final class Company {
	private int id;
	private String name;
	
	public Company (int id, String name){
		this.id = id;
		this.name = name;
	}
	
	
	public int getId() {
		return this.id;
	}
	
	
	
	public void setId (int id) {
		this.id = id;
	}
	
	
	
	public String getName() {
		return this.name;
	}
	
	
	public void setName (String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("| %s | Name : %-60s |" ,
				this.id,
				this.name);
	}
	
	
	public boolean equals(Company c) {
		return this.id == c.id && this.name.equals(c.name);
	}
	
	@Override
	public int hashCode() {
		int hash = 1;
		hash += hash*id;
		hash += hash*this.name.hashCode();
		return hash;
	}
	

}
