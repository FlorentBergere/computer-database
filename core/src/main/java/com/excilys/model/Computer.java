package com.excilys.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "introduced")
	private LocalDate introduced;
	
	@Column(name = "discontinued")
	private LocalDate discontinued;
	
    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id")
	private Company company;
	
	public enum atributes {
		ID ("computer.id"),
		NAME ("computer.name"),
		INTRODUCED ("computer.introduced"),
		DISCONTINUED ("computer.discontinued"),
		COMPANY ("computer.company_id");
		
		private final String atribute; 
		private atributes (String name) {
			this.atribute = name;
		} 
		public String getAtribute() {
			return this.atribute;
		}
	}
	
	public Computer() {}
	
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinued,Company company){
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
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


	
	public Company getCompany() {
		return this.company;
	}

	
	public int getCompanyId() {
		return this.company.getId();
	}
	
	public String getComanyName() {
		return this.company.getName();
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	

	
	@Override
	public String toString() {
		return String.format("| %3d | Name : %-65s | Intro : %10s | Disco : %10s | Comp_ID %3s",
			this.getId(),
			this.name,
			String.valueOf(this.introduced),
			String.valueOf(this.discontinued),
			company == null ? null : this.company.toString());
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	
	
}
