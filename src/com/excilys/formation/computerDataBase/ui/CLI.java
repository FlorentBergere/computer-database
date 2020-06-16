package com.excilys.formation.computerDataBase.ui;

import java.sql.Date;
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
					
				case 4:
					System.out.println("Enter the computer Id : ");
					System.out.print(">");
					int id = in.nextInt();
					System.out.println("Enter the computer name : ");
					System.out.print(">");
					in.next();
					String name = in.nextLine();
					System.out.println("Ceci est la valeur de name : " + name);
					System.out.println("Enter the date of the computer was introduce yyyy-mm-dd or (null): ");
					System.out.print(">");
					Date introduced = Date.valueOf(in.next());
					System.out.println("Enter the date of the computer was discontinued yyyy-mm-dd or (null): ");
					System.out.print(">");
					Date discontinued = Date.valueOf(in.next());
					System.out.println("Enter the compagny Id : ");
					System.out.print(">");
					int compagnyId = in.nextInt();
					computerDAO.addComputer(id,name,introduced,discontinued,compagnyId);
					
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



