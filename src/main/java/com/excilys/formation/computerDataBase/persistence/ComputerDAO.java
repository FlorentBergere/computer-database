package com.excilys.formation.computerDataBase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerDataBase.mapper.ComputerMapper;
import com.excilys.formation.computerDataBase.mapper.DateMapper;
import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.Page;
import com.excilys.formation.computerDataBase.service.ConnectionFactory;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class ComputerDAO {
	@Autowired
	ConnectionFactory connectionFactory;
	
    @Autowired
	HikariDataSource hikariDataSource;
    
    @Autowired
    ComputerMapper computerMapper;
    
    private final static String QUERY_FIND_COMPUTER = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id";
    private final static String QUERY_FINDBYID = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id = :id";
    private final static String QUERY_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id)" + " VALUES (:name, :introduced, :discontinued, :companyId)";
    private final static String QUERY_UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
    private final static String QUERY_DELETE = "DELETE FROM computer WHERE id=:id";
    private final static String QUERY_FINDBYPAGE_COMPUTER = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id LIMIT ? OFFSET ?";
    private final static String QUERY_FINDBYPAGE_COMPUTER_BYPAGE_FINDBYNAME = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE ? LIMIT ? OFFSET ?";
    private final static String QUERY_FINDBYPAGE_COMPUTER_BYPAGE_ORDER = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id ORDER BY $$ORDER$$ LIMIT ? OFFSET ?"; 
    private final static String QUERY_FINDBYPAGE_COMPUTER_BYPAGE_FINDBYNAME_ORDER = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE ? ORDER BY $$ORDER$$ LIMIT ? OFFSET ?";
    private final static String QUERY_COUNT_COMPUTER = "SELECT count(*) as nbComputer FROM computer";
    
    public ComputerDAO () {
    }
    
    public List<Computer> findAll () { 	
    	List<Computer> result;
    	JdbcTemplate jdbc = new JdbcTemplate(hikariDataSource);
    	result = jdbc.query(QUERY_FIND_COMPUTER, computerMapper);
		return result;
		
    }
    
    public List<Computer> fingByID (int id) {
    	List<Computer> result;
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", id, Types.INTEGER);
    	NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(hikariDataSource);
    	result = jdbc.query(QUERY_FINDBYID, parameters, computerMapper);
    	return result;
    }
    
    public void add (String name, LocalDate introduced, LocalDate discontinued, int companyId) { 	
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("name", name);
    	parameters.addValue("introduced", introduced);
    	parameters.addValue("discontinued", discontinued);
    	parameters.addValue("companyId", companyId);
    	
    	NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(hikariDataSource);
    	jdbc.update(QUERY_INSERT, parameters);
    }
    
    public void update (int id, String name, LocalDate introduced, LocalDate discontinued, int companyId) {
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", id);
    	parameters.addValue("name", name);
    	parameters.addValue("introduced", introduced);
    	parameters.addValue("discontinued", discontinued);
    	parameters.addValue("companyId", companyId);
    	
    	NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(hikariDataSource);
    	jdbc.update(QUERY_UPDATE, parameters);
    }
    
    public void delete (int id) {
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", id, Types.INTEGER);
    	NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(hikariDataSource); 
    	jdbc.update(QUERY_DELETE, parameters);
    }
    
    
    public List<Computer> findAllByPage (Page page) {
    	List<Computer> result =null;
    	
    	return null;
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
    			PreparedStatement stmt = connection.prepareStatement(QUERY_FINDBYPAGE_COMPUTER_BYPAGE_ORDER.replace("$$ORDER$$", atribute.getAtribute()));
    			ResultSet rset = extracted(offset, nbEntry, stmt);
    			)
    		{
    	//	stmt.setString(1, atribute.getAtribute());
    		while(rset.next()) {
    			result.add(ComputerMapper.rsetToComputer(rset));
    		}
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
		return result;
    }

	private ResultSet extracted(int offset, int nbEntry, PreparedStatement stmt) throws SQLException {
		stmt.setInt(1, nbEntry);
		stmt.setInt(2, offset);
		System.out.println(stmt.toString());
		ResultSet rset = stmt.executeQuery();
		return rset;
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
    	JdbcTemplate jdbc = new JdbcTemplate(hikariDataSource);
    	result = jdbc.queryForObject(QUERY_COUNT_COMPUTER, Integer.class);
    	return result;
    }
}
