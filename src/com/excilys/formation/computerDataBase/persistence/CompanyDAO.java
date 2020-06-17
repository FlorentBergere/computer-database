package com.excilys.formation.computerDataBase.persistence;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.service.Connection;

public class CompanyDAO {
	Connection con = new Connection();
    private final static String QUERY_FIND_COMPANY = "SELECT * FROM company";
    
    private static List<Company> companyCollection;
    
    public CompanyDAO() {
    	companyCollection = new ArrayList<Company>();
    }
    
    public void findCompany () throws SQLException{
    	Statement stmt = con.getConnection().createStatement();
		ResultSet rset = stmt.executeQuery(QUERY_FIND_COMPANY);
		
		while(rset.next()) {
			Company company = new Company(rset.getInt("id"),rset.getString("name"));
			companyCollection.add(company);
		}
		
		
		
    }
    
    public List<Company> getCompanyCollection (){
    	return CompanyDAO.companyCollection;
    }
}
