package com.excilys.formation.computerDataBase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.mapper.ComputerMapper;
import com.excilys.formation.computerDataBase.mapper.DateMapper;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.service.Connection;

public class ComputerDAO {
	Connection con = new Connection();
    private final static String QUERY_FIND_COMPUTER = "SELECT * FROM computer";
    private final static String QUERY_FINDBYID = "SELECT  * FROM computer WHERE id = ?";
    private final static String QUERY_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id)" + " VALUES (?, ?, ?, ?)";
    private final static String QUERY_UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
    private final static String QUERY_DELETE = "DELETE FROM computer WHERE id=?";
    
    
    public List<Computer> findAll () {
    	ArrayList<Computer> result = new ArrayList<Computer>();
    	try {
    		Statement stmt = con.getConnection().createStatement();
    		ResultSet rset = stmt.executeQuery(QUERY_FIND_COMPUTER);
    		while(rset.next()) {
    			result.add(ComputerMapper.rsetToComputer(rset));
    		}
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
		return result;
		
    }
    
    public List<Computer> fingByID (int id) {
    	ArrayList<Computer> result = new ArrayList<Computer>();
    	try {
	    	PreparedStatement stmt = con.getConnection().prepareStatement(QUERY_FINDBYID);
	    	stmt.setInt(1, id);
	    	
	    	ResultSet rset = stmt.executeQuery();
	    	
	    	while (rset.next()) {
	    		result.add(ComputerMapper.rsetToComputer(rset));
	    	}
	    }catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}
    	return result;
    }
    
    public void add (String name, LocalDate introduced, LocalDate discontinued, int compagnyId) { 	
    	try {
    		PreparedStatement stmt = con.getConnection().prepareStatement(QUERY_INSERT);
    		stmt.setString(1, name);
	    	stmt.setDate(2, DateMapper.localDateTosqlDate(introduced));
	    	stmt.setDate(3, DateMapper.localDateTosqlDate(discontinued));
	    	stmt.setInt(4, compagnyId);
        	stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	
    }
    
    public void update (int id, String name, LocalDate introduced, LocalDate discontinued, int compagnyId) {
    	try {
        	PreparedStatement stmt = con.getConnection().prepareStatement(QUERY_UPDATE);
        	stmt.setString(1, name);
	    	stmt.setDate(2, DateMapper.localDateTosqlDate(introduced));
	    	stmt.setDate(3, DateMapper.localDateTosqlDate(discontinued));
	    	stmt.setInt(4, compagnyId);
	    	stmt.setInt(5, id);
        	stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    public void delete (int id) {
    	try {
			PreparedStatement stmt = con.getConnection().prepareStatement(QUERY_DELETE);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    
    private final static String QUERY_FINDBYPAGE_COMPUTER = "SELECT * FROM computer LIMIT ? OFFSET ?";
    public List<Computer> findAllByPage (int offset, int nbEntry) {
    	ArrayList<Computer> result = new ArrayList<Computer>();
    	try {
    		PreparedStatement stmt = con.getConnection().prepareStatement(QUERY_FINDBYPAGE_COMPUTER);
    		stmt.setInt(1, nbEntry);
    		stmt.setInt(2, offset);
    		ResultSet rset = stmt.executeQuery();
    		while(rset.next()) {
    			result.add(ComputerMapper.rsetToComputer(rset));
    		}
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
		return result;
    }
    
    private final static String QUERY_COUNT_COMPUTER = "SELECT count(*) as nbComputer FROM computer";
    public int countEntry (){
    	int result = 0;
    	try {
			Statement stmt = con.getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_COUNT_COMPUTER);
			rset.first();
			result = rset.getInt("nbComputer");
			System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return result;
    }
}
