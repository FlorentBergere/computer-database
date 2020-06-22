package com.excilys.formation.computerDataBase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.mapper.CompanyMapper;
import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.service.Connection;

public class CompanyDAO {
	Connection con = new Connection();
    private final static String QUERY_FIND_COMPANY = "SELECT * FROM company";
    
    private static List<Company> companyCollection;
    
    public CompanyDAO() {
    	companyCollection = new ArrayList<Company>();
    }
    
    public void findCompany () {
    	try {
    		Statement stmt = con.getConnection().createStatement();
    		ResultSet rset = stmt.executeQuery(QUERY_FIND_COMPANY);
    		
    		while(rset.next()) {
    			companyCollection.add(CompanyMapper.rsetToCompany(rset));
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
    			
    }
    
    public List<Company> getCompanyCollection (){
    	return CompanyDAO.companyCollection;
    }
    
    private final static String QUERY_FINDBYPAGE_COMPANY = "SELECT * FROM company LIMIT ? OFFSET ?";
    public List<Company> findAllByPage (int offset, int nbEntry) {
    	ArrayList<Company> result = new ArrayList<Company>();
    	try {
    		PreparedStatement stmt = con.getConnection().prepareStatement(QUERY_FINDBYPAGE_COMPANY);
    		stmt.setInt(1, nbEntry);
    		stmt.setInt(2, offset);
    		ResultSet rset = stmt.executeQuery();
    		while(rset.next()) {
    			result.add(CompanyMapper.rsetToCompany(rset));
    		}
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
		return result;
    }
    
    private final static String QUERY_COUNT_COMPANY = "SELECT count(*) as nbCompany FROM company";
    public int countEntry (){
    	int result = 0;
    	try {
			Statement stmt = con.getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_COUNT_COMPANY);
			rset.first();
			result = rset.getInt("nbCompany");
			System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return result;
    }
}
