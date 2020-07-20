package com.excilys.formation.computerDataBase.ui;

import java.time.LocalDate;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.formation.computerDataBase.configuration.HibernateConfig;
import com.excilys.formation.computerDataBase.configuration.SpringConfig;
import com.excilys.formation.computerDataBase.mapper.DateMapper;
import com.excilys.formation.computerDataBase.service.CompanyService;
import com.excilys.formation.computerDataBase.service.ComputerService;


public class CLI {
	private static final Logger logger = LoggerFactory.getLogger(CLI.class);
	boolean quit = false;
	int entry;
	boolean page;
	Scanner in = new Scanner(System.in);
	private ComputerService computerService;
	private CompanyService companyService;

	int id;
	String name;
	LocalDate introduced;
	LocalDate discontinued;
	int compagnyId;
	
	
	@Autowired
	public CLI (ComputerService computerService, CompanyService companyService) {
		this.computerService = computerService;
		this.companyService = companyService;
	}
	
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class, HibernateConfig.class);
		CLI cli = new CLI(context.getBean(ComputerService.class),context.getBean(CompanyService.class));
		cli.showCommand();
		
		while(!cli.quit) {
			
			System.out.print(">");
			
			cli.entry = cli.in.nextInt();

			switch(cli.entry) {
				case 0 : 					
					System.out.println("Closing application.");
					cli.quit = true;
					break;
				case 1: 		
					cli.printAllComputer();
					break;
				case 2:
					cli.printAllComputerByPage();
					break;
				case 3:
					cli.printAllCompany();
					break;
				case 4:
					cli.printAllCompanyByPage();
					break;
				case 5:
					cli.printComputer();
					break;
				case 6:
					cli.createComputer();
					break;	
				case 7:
					cli.updateComputer();
					break;
				case 8:
					cli.deleteComputer();
					break;
				case 9:
					cli.deleteCompany();
					break;
				default: 
					System.out.println("Bad argument");
					break;
			}
		}
		
		cli.in.close();
		
	}
	
	private void showCommand () {
		System.out.println("List of commands (type the corresponding number to select one) : ");
		System.out.println(" 0 - quit");
		System.out.println(" 1 - List all computer ");
		System.out.println(" 2 - List computer by page");
		System.out.println(" 3 - List company ");
		System.out.println(" 4 - List computer by page");
		System.out.println(" 5 - Show computer detail (you will need the Id the computer )");
		System.out.println(" 6 - Create a computer");
		System.out.println(" 7 - Update a computer");
		System.out.println(" 8 - Delete a computer");
		System.out.println(" 9 - Delete a company");
		
	}
	
	private void printAllComputer () {
		for(String s : computerService.listAll()) {
			System.out.println(s);
		}
	}
	
	private void printAllComputerByPage () {
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
					for(String s : computerService.previousPage()) {
						System.out.println(s);
					}
					break;
				default:
					//TODO print menu des page
					break;
			}
		}
	}
	
	private void printAllCompany () {
		for(String s : companyService.listAll()) {
			System.out.println(s);
		}
	}
	
	private void printAllCompanyByPage () {
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
					for(String s : companyService.previousPage()) {
						System.out.println(s);
					}
					break;
				default:
					//TODO print menu des page
					break;
			}
		}
	}
	
	
	private void printComputer () {
		System.out.println("Enter the ID of the computer : ");
		System.out.print(">");
		entry = in.nextInt();
		for(String s : computerService.getComputerByID(entry)) {
			System.out.println(s);
		}
	}
	
	
	private void createComputer () {
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
	}
	
	
	private void updateComputer () {
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
	}
	
	
	private void deleteComputer () {
		System.out.println("Enter the computer ID : ");
		System.out.print(">");
		id = in.nextInt();
		computerService.delete(id);
	}
	
	private void deleteCompany () {
		System.out.println("Enter the company ID : ");
		compagnyId = in.nextInt();
		companyService.delete(compagnyId);
	}
}



