package com.excilys.formation.computerDataBase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.excilys.formation.computerDataBase.collection.CompanyCollection;
import com.excilys.formation.computerDataBase.model.Company;

public class CompanyDAO {
	private static String URL = "jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC";
    private static String LOGIN = "admincdb";
    private static String PASSWORD = "azerty1234";
    private final static String QUERY_FIND_COMPANY = "SELECT * FROM company";
    
    public CompanyCollection findCompany () throws SQLException{
	    Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
		Statement stmt = con.createStatement();
		ResultSet rset = stmt.executeQuery(QUERY_FIND_COMPANY);
		
		CompanyCollection companys = new CompanyCollection();
		
		while(rset.next()) {
			Company company = new Company(rset.getInt("id"),rset.getString("name"));
			companys.addCompany(company);
		}
		
		return companys;
    }
}
