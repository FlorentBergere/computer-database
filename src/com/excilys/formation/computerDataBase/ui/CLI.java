package com.excilys.formation.computerDataBase.ui;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.persistence.CompanyDAO;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;
import com.excilys.formation.computerDataBase.service.Connection;
import com.excilys.formation.computerDataBase.service.CompanyService;
import com.excilys.formation.computerDataBase.service.ComputerService;

public class CLI {
	public static void main(String[] args) throws SQLException {
		boolean quit = false;
		int entry;
		Scanner in = new Scanner(System.in);
		Connection con = new Connection();
		ComputerService computerService = new ComputerService();
		CompanyService compagnyService = new CompanyService();
		
		
		
		showCommand();
		
		while(!quit) {
			
			System.out.print(">");
			
			entry = in.nextInt();

			switch(entry) {
			
				case 1: 		
					for(String s : computerService.listAll()) {
						System.out.println(s);
					}
					break;
				
				case 2:
					for(String s : compagnyService.listAll()) {
						System.out.println(s);
					}
					break;
				
				case 3:
					//TODO utiliser le computerService
					System.out.println("Enter the ID of the computer : ");
					System.out.print(">");
					entry = in.nextInt();
					for(String s : computerService.getComputerByID(entry)) {
						System.out.println(s);
					}
					break;
					
				case 4:
					System.out.println("Enter the computer name : ");
					System.out.print(">");
					String name = in.next() + in.nextLine();
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
					computerService.add(name,introduced,discontinued,compagnyId);
					
					break;
					
				case 5:
					
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
		
		con.closeConnection();
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



