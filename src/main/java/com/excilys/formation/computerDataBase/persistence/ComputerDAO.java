package com.excilys.formation.computerDataBase.persistence;

import java.sql.Types;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerDataBase.mapper.ComputerMapper;
import com.excilys.formation.computerDataBase.mapper.DateMapper;
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
    
    @Autowired
    NamedParameterJdbcTemplate jdbc;
    
    private final static String QUERY_FIND_COMPUTER = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id";
    private final static String QUERY_WHERE_ATTRIBUTE_LIKE = " WHERE computer.name LIKE '%:like%'";
    private final static String QUERY_ORDER_BY = " ORDER BY :attribute ";
    private final static String QUERY_LIMIT_OFFSET = " LIMIT :limit OFFSET :offset" ;
    private final static String QUERY_FINDBYID = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id = :id";
    private final static String QUERY_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id)" + " VALUES (:name, :introduced, :discontinued, :companyId)";
    private final static String QUERY_UPDATE = "UPDATE computer SET name=:name, introduced=:introduced, discontinued=:discontinued, company_id=:companyId WHERE id=:computerId";
    private final static String QUERY_DELETE = "DELETE FROM computer WHERE id=:id";
    private final static String QUERY_COUNT_COMPUTER = "SELECT count(*) as nbComputer FROM computer";
    
    
    
    public List<Computer> findAll () { 	
    	List<Computer> result;
    	result = jdbc.query(QUERY_FIND_COMPUTER, computerMapper);
		return result;
		
    }
    
    public List<Computer> fingByID (int id) {
    	List<Computer> result;
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", id, Types.INTEGER);
    	result = jdbc.query(QUERY_FINDBYID, parameters, computerMapper);
    	return result;
    }
    
    public boolean add (String name, LocalDate introduced, LocalDate discontinued, int companyId) { 	
    	boolean success = false;
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("name", name);
    	parameters.addValue("introduced", DateMapper.localDateTosqlDate(introduced));
    	parameters.addValue("discontinued", DateMapper.localDateTosqlDate(discontinued));
    	parameters.addValue("companyId", companyId);
    	
    	int result = jdbc.update(QUERY_INSERT, parameters);
    	if ( result > 0) {
    		success = true;
    	}
    	return success;
    }
    
    public boolean update (int id, String name, LocalDate introduced, LocalDate discontinued, int companyId) {
    	boolean success = false;
    	int result = 0;
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("computerId", id);
    	parameters.addValue("name", name);
    	parameters.addValue("introduced", DateMapper.localDateTosqlDate(introduced));
    	parameters.addValue("discontinued", DateMapper.localDateTosqlDate(discontinued));
    	parameters.addValue("companyId", companyId);
    	
    	result = jdbc.update(QUERY_UPDATE, parameters);
    	if (result > 0) {
    		success = true;
    	}
    	return success;
    }
    
    public void delete (int id) {
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", id, Types.INTEGER);
    	jdbc.update(QUERY_DELETE, parameters);
    }
    
    
    public List<Computer> findAllByPage (Page page) {
    	List<Computer> result =null;
    	String query = QUERY_FIND_COMPUTER;
    	if( page.getSearch() != null ) {
    		query += QUERY_WHERE_ATTRIBUTE_LIKE.replace(":like", page.getSearch());
    	}
    	if( page.getAttributeToOrder() != null ) {
    		query += QUERY_ORDER_BY.replace(":attribute", page.getAttributeToOrder());
    		query += page.getCurrentOrder();
    	}
    	query += QUERY_LIMIT_OFFSET
    			.replace(":limit", Integer.valueOf(page.getPageLength()).toString())
    			.replace(":offset", Integer.valueOf(page.getOffset()).toString());
    	
    	result = jdbc.query(query, computerMapper);
    	return result;
    }

    
    public int countEntry (){
    	int result = 0;
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	result = jdbc.queryForObject(QUERY_COUNT_COMPUTER,parameters, Integer.class);
    	return result;
    }
}
