package com.excilys.formation.computerDataBase.ui;

import java.time.LocalDate;
import java.util.Scanner;


import com.excilys.formation.computerDataBase.service.Connection;
import com.excilys.formation.computerDataBase.mapper.ComputerMapper;
import com.excilys.formation.computerDataBase.mapper.DateMapper;
import com.excilys.formation.computerDataBase.service.CompanyService;
import com.excilys.formation.computerDataBase.service.ComputerService;

public class CLI {
	public static void main(String[] args) {
		boolean quit = false;
		int entry;
		boolean page;
		Scanner in = new Scanner(System.in);
		Connection con = new Connection();
		ComputerService computerService = new ComputerService();
		CompanyService companyService = new CompanyService();
		
		int id;
		String name;
		LocalDate introduced;
		LocalDate discontinued;
		int compagnyId;
		
		
		
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
					for(String s : computerService.findAllByPage()) {
						System.out.println(s);
					}
					page = true;
					while(page) {
						entry = in.nextInt();
						switch (entry) {
							case 0:
								page = false;
								break;
							case 1:
								for(String s : computerService.nextPage()) {
									System.out.println(s);
								}
								break;
							case 2:
								for(String s : computerService.priviousPage()) {
									System.out.println(s);
								}
								break;
							default:
								//TODO print menu des page
								break;
						}
					}
					break;
				case 3:
					for(String s : companyService.listAll()) {
						System.out.println(s);
					}
					break;
				case 4:
					for(String s : companyService.findAllByPage()) {
						System.out.println(s);
					}
					page = true;
					while(page) {
						entry = in.nextInt();
						switch (entry) {
							case 0:
								page = false;
								break;
							case 1:
								for(String s : companyService.nextPage()) {
									System.out.println(s);
								}
								break;
							case 2:
								for(String s : companyService.priviousPage()) {
									System.out.println(s);
								}
								break;
							default:
								//TODO print menu des page
								break;
						}
					}
					break;
				case 5:
					System.out.println("Enter the ID of the computer : ");
					System.out.print(">");
					entry = in.nextInt();
					for(String s : computerService.getComputerByID(entry)) {
						System.out.println(s);
					}
					break;
					
				case 6:
					System.out.println("Enter the computer name : ");
					System.out.print(">");
					name = in.next() + in.nextLine();
					System.out.println("Enter the date of the computer was introduce yyyy-mm-dd or (null): ");
					System.out.print(">");
					introduced = DateMapper.stringToLocalDate(in.next());
					System.out.println("Enter the date of the computer was discontinued yyyy-mm-dd or (null): ");
					System.out.print(">");
					discontinued = DateMapper.stringToLocalDate(in.next());
					System.out.println("Enter the compagny Id : ");
					System.out.print(">");
					compagnyId = in.nextInt();
					computerService.add(name,introduced,discontinued,compagnyId);
					
					break;
					
				case 7:
					System.out.println("Enter the computer ID : ");
					System.out.print(">");
					id = in.nextInt();
					System.out.println("Enter the new computer name : ");
					System.out.print(">");
					name = in.next() + in.nextLine();
					System.out.println("Enter the new date of the computer was introduce yyyy-mm-dd or (null): ");
					System.out.print(">");
					introduced = DateMapper.stringToLocalDate(in.next());
					System.out.println("Enter the new date of the computer was discontinued yyyy-mm-dd or (null): ");
					System.out.print(">");
					discontinued = DateMapper.stringToLocalDate(in.next());
					System.out.println("Enter the new compagny Id : ");
					System.out.print(">");
					compagnyId = in.nextInt();
					computerService.update(id,name,introduced,discontinued,compagnyId);
					
					break;
				case 8:
					System.out.println("Enter the computer ID : ");
					System.out.print(">");
					id = in.nextInt();
					computerService.delete(id);
					break;
					
				
				case 9:
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
		System.out.println(" 1 - List all computer ");
		System.out.println(" 2 - List computer by page");
		System.out.println(" 3 - List company ");
		System.out.println(" 4 - List computer by page");
		System.out.println(" 5 - Show computer detail (you will need the Id the computer )");
		System.out.println(" 6 - Create a computer");
		System.out.println(" 7 - Update a computer");
		System.out.println(" 8 - Delete a computer");
		System.out.println(" 9 - quit");
		
	}
}



