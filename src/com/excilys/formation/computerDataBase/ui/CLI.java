package com.excilys.formation.computerDataBase.ui;

import java.sql.SQLException;
import java.util.List;


import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.persistence.CompanyDAO;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

public class CLI {
	public static void main(String[] args) throws SQLException {
		
		CompanyDAO companyDAO = new CompanyDAO();
		companyDAO.findCompany();
		List<Company> companyCollection = companyDAO.getCompanyCollection();
		
		ComputerDAO computerDAO = new ComputerDAO();
		computerDAO.findComputer();
		List<Computer> computerCollection = computerDAO.getComputerCollection();
		
		if(args.length == 0) {
			System.out.println("list des commande : ");
			System.out.println("  --listComputer ");
			System.out.println("  --listCompany ");
			System.out.println("  --showComputerDetail Id");
			
		}else if (args.length >= 1) {
			switch(args[0]) {
				
				case "--listComputer": 		
					for(Computer c : computerCollection) {
						System.out.println(c);
					}
					break;
				
				case "--listCompany":
					for(Company c : companyCollection) {
						System.out.println(c);
					}
					break;
				
				case "--showComputerDetail":
					
						if (args.length != 2 ) {
							System.out.println("Bad argument");
						}
						else {
							String result = null;
							for(Computer c : computerCollection) {
								if ( c.getId() == Integer.valueOf(args[1]) ) {
									result = c.toString();
								}
							}
							if (result !=null) {
								System.out.println(result);
							}else {
								System.out.println("This computer doesn't exist.");
							}
						}
						break;
						
				default: 
					System.out.println("Bad argument");
					break;
			}
		}
		else {
			System.out.println("Bad argument");
		}
	
	}
}



