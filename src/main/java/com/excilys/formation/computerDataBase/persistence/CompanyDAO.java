package com.excilys.formation.computerDataBase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.mapper.CompanyMapper;
import com.excilys.formation.computerDataBase.mapper.ComputerMapper;
import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.service.ConnectionFactory;

public class CompanyDAO {
	ConnectionFactory connectionFactory;
    private final static String QUERY_FIND_COMPANY = "SELECT * FROM company";
    private final static String QUERY_FINDBYID = QUERY_FIND_COMPANY + " WHERE id = ?";
    private final static String QUERY_FINDBYPAGE_COMPANY = "SELECT * FROM company LIMIT ? OFFSET ?";
    private final static String QUERY_COUNT_COMPANY = "SELECT count(*) as nbCompany FROM company";
    
    
    public CompanyDAO () {
    	this.connectionFactory = ConnectionFactory.getInstance();
    }
        
    public List<Company> findCompany () {
    	ArrayList<Company> result = new ArrayList<Company>();
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_FIND_COMPANY))
    		{
    		ResultSet rset = stmt.executeQuery();
    		while(rset.next()) {
    			result.add(CompanyMapper.rsetToCompany(rset));
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return result;
    }
    
    public List<Company> fingByID (int id) {
    	ArrayList<Company> result = new ArrayList<Company>();
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_FINDBYID))
    		{
	    	stmt.setInt(1, id);
	    	ResultSet rset = stmt.executeQuery();
	    	while (rset.next()) {
	    		result.add(CompanyMapper.rsetToCompany(rset));
	    	}
	    }catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}
    	
    	return result;
    }
    

    public List<Company> findAllByPage (int offset, int nbEntry) {
    	ArrayList<Company> result = new ArrayList<Company>();
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_FINDBYPAGE_COMPANY))
    		{
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
    

    public int countEntry (){
    	int result = 0;
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_COUNT_COMPANY))
    		{
			ResultSet rset = stmt.executeQuery();
			rset.first();
			result = rset.getInt("nbCompany");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return result;
    }
}
