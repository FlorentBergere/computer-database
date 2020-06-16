package com.excilys.formation.computerDataBase.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.persistence.CompanyDAO;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

public class CLI {
	public static void main(String[] args) throws SQLException {
		boolean quit = false;
		int entry;
		Scanner in = new Scanner(System.in);
		
		CompanyDAO companyDAO = new CompanyDAO();
		companyDAO.findCompany();
		List<Company> companyCollection = companyDAO.getCompanyCollection();
		
		ComputerDAO computerDAO = new ComputerDAO();
		computerDAO.findComputer();
		List<Computer> computerCollection = computerDAO.getComputerCollection();
		
		
		showCommand();
		
		while(!quit) {
			
			System.out.print(">");
			
			entry = in.nextInt();

			switch(entry) {
			
				case 1: 		
					for(Computer c : computerCollection) {
						System.out.println(c);
					}
					break;
				
				case 2:
					for(Company c : companyCollection) {
						System.out.println(c);
					}
					break;
				
				case 3:
					entry = in.nextInt();
					String result = null;
					for(Computer c : computerCollection) {
						if ( c.getId() == entry ) {
							result = c.toString();
						}
					}
					if (result !=null) {
						System.out.println(result);
					}else {
						System.out.printf("No match found for computer with id n°: %d\n",entry);
					}
					break;
				case 7:
					System.out.println("Closing application.");
					quit = true;
					break;
				default: 
					System.out.println("Bad argument");
					break;
			}
		}
		
		in.close();
		
	}
	
	private static void showCommand () {
		System.out.println("List of commands (type the corresponding number to select one) : ");
		System.out.println(" 1 - List computer ");
		System.out.println(" 2 - List company ");
		System.out.println(" 3 - Show computer detail (you will need the Id the computer )");
		System.out.println(" 4 - Create a computer");
		System.out.println(" 5 - Update a computer");
		System.out.println(" 6 - Delete a computer");
		System.out.println(" 7 - quit");
		
	}
}



