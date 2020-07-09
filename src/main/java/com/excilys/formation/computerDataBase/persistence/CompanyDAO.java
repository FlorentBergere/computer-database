package com.excilys.formation.computerDataBase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerDataBase.mapper.CompanyMapper;
import com.excilys.formation.computerDataBase.mapper.ComputerMapper;
import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.service.ConnectionFactory;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class CompanyDAO {
	@Autowired
	ConnectionFactory connectionFactory;
	
    @Autowired
	HikariDataSource hikariDataSource;
    
    @Autowired
    CompanyMapper companyMapper;
    
    private final static String QUERY_FIND_COMPANY = "SELECT * FROM company";
    private final static String QUERY_FINDBYID = QUERY_FIND_COMPANY + " WHERE id = :id";
    private final static String QUERY_FINDBYPAGE_COMPANY = "SELECT * FROM company LIMIT ? OFFSET ?";
    private final static String QUERY_COUNT_COMPANY = "SELECT count(company.id) as nbCompany FROM company";
    private final static String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE company_id = :id";
    private final static String QUERY_DELETE_COMPANY = "DELETE FROM company WHERE id = :id";
    

    public CompanyDAO () {
    }
        
    
    public List<Company> findCompany () {
    	List<Company> result = null;
    	JdbcTemplate jdbc = new JdbcTemplate(hikariDataSource);
    	result = jdbc.query(QUERY_FIND_COMPANY, companyMapper);
    	return result;
    }
    
    public List<Company> fingByID (int id) {
    	List<Company> result;
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", id, Types.INTEGER);
    	NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(hikariDataSource);
    	result = jdbc.query(QUERY_FINDBYID, parameters, companyMapper);
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
    

    
    public int countEntry() {
    	int result = 0;
    	JdbcTemplate jdbc = new JdbcTemplate(hikariDataSource);
    	result = jdbc.queryForObject(QUERY_COUNT_COMPANY, Integer.class);
    	return result;
    }
    
    public void deleteCompany (Company company) {

    	NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(hikariDataSource); 
    	
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", company.getId(), Types.INTEGER);
    	
    	jdbc.update(QUERY_DELETE_COMPUTER, parameters);
    	jdbc.update(QUERY_DELETE_COMPANY, parameters);
    	

    	
    }
}
