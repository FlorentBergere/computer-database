package com.excilys.formation.computerDataBase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.excilys.formation.computerDataBase.mapper.ComputerMapper;
import com.excilys.formation.computerDataBase.mapper.DateMapper;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.service.ConnectionFactory;


public class ComputerDAO {
	ConnectionFactory connectionFactory;
    private final static String QUERY_FIND_COMPUTER = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id";
    private final static String QUERY_FINDBYID = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id = ?";
    private final static String QUERY_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id)" + " VALUES (?, ?, ?, ?)";
    private final static String QUERY_UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
    private final static String QUERY_DELETE = "DELETE FROM computer WHERE id=?";
    private final static String QUERY_FINDBYPAGE_COMPUTER = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id LIMIT ? OFFSET ?";
    private final static String QUERY_FINDBYPAGE_COMPUTER_BYPAGE_FINDBYNAME = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE ? LIMIT ? OFFSET ?";
    private final static String QUERY_FINDBYPAGE_COMPUTER_BYPAGE_ORDER = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id ORDER BY $$ORDER$$ LIMIT ? OFFSET ?"; 
    private final static String QUERY_FINDBYPAGE_COMPUTER_BYPAGE_FINDBYNAME_ORDER = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE ? ORDER BY $$ORDER$$ LIMIT ? OFFSET ?";
    private final static String QUERY_COUNT_COMPUTER = "SELECT count(*) as nbComputer FROM computer";
    
    public ComputerDAO () {
    	this.connectionFactory = ConnectionFactory.getInstance();
    }
    
    public List<Computer> findAll () {
    	ArrayList<Computer> result = new ArrayList<Computer>();
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_FIND_COMPUTER))
    		{
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
    
    public List<Computer> fingByID (int id) {
    	ArrayList<Computer> result = new ArrayList<Computer>();
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_FINDBYID))
    		{
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
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_INSERT))
    		{
    		stmt.setString(1, name);
	    	stmt.setDate(2, DateMapper.localDateTosqlDate(introduced));
	    	stmt.setDate(3, DateMapper.localDateTosqlDate(discontinued));
	    	stmt.setInt(4, compagnyId);
        	stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}   	
    }
    
    public void update (int id, String name, LocalDate introduced, LocalDate discontinued, int compagnyId) {
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_UPDATE))
    		{
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
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_DELETE))
    		{
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    

    public List<Computer> findAllByPage (int offset, int nbEntry) {
    	ArrayList<Computer> result = new ArrayList<Computer>();
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_FINDBYPAGE_COMPUTER))
    		{
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
    
    public List<Computer> findAllByPage (int offset, int nbEntry, Computer.atributes atribute) {
    	ArrayList<Computer> result = new ArrayList<Computer>();
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_FINDBYPAGE_COMPUTER_BYPAGE_ORDER.replace("$$ORDER$$", atribute.getAtribute())))
    		{
    	//	stmt.setString(1, atribute.getAtribute());
    		stmt.setInt(1, nbEntry);
    		stmt.setInt(2, offset);
    		System.out.println(stmt.toString());
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
    
    public List<Computer> findAllByPage (int offset, int nbEntry, String name) {
    	ArrayList<Computer> result = new ArrayList<Computer>();
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_FINDBYPAGE_COMPUTER_BYPAGE_FINDBYNAME))
    		{
    		stmt.setString(1, "%" + name + "%");
    		stmt.setInt(2, nbEntry);
    		stmt.setInt(3, offset);
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
    
    public List<Computer> findAllByPage (int offset, int nbEntry, String name,Computer.atributes atribute) {
    	ArrayList<Computer> result = new ArrayList<Computer>();
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_FINDBYPAGE_COMPUTER_BYPAGE_FINDBYNAME_ORDER.replace("$$ORDER$$", atribute.getAtribute())))
    		{
    		stmt.setString(1, "%" + name + "%");
    		//stmt.setString(2, atribute.getAtribute());
    		stmt.setInt(2, nbEntry);
    		stmt.setInt(3, offset);
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
    
    
   
    public int countEntry (){
    	int result = 0;
    	try (	Connection connection = connectionFactory.getConnection();
    			PreparedStatement stmt = connection.prepareStatement(QUERY_COUNT_COMPUTER))
    		{
			ResultSet rset = stmt.executeQuery();
			rset.first();
			result = rset.getInt("nbComputer");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return result;
    }
}
