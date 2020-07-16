package com.excilys.formation.computerDataBase.persistence;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.computerDataBase.mapper.CompanyMapper;
import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Page;
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
    
    @Autowired
    NamedParameterJdbcTemplate jdbc;
    
    private final static String QUERY_FIND_COMPANY = "SELECT * FROM company";
    private final static String QUERY_FINDBYID = QUERY_FIND_COMPANY + " WHERE id = :id";
    private final static String QUERY_FINDBYPAGE_COMPANY = "SELECT * FROM company LIMIT :limit OFFSET :offset";
    private final static String QUERY_COUNT_COMPANY = "SELECT count(company.id) as nbCompany FROM company";
    private final static String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE company_id = :id";
    private final static String QUERY_DELETE_COMPANY = "DELETE FROM company WHERE id = :id";
    

    public CompanyDAO () {
    }
        
    
    public List<Company> findCompany () {
    	List<Company> result = null;
    	result = jdbc.query(QUERY_FIND_COMPANY, companyMapper);
    	return result;
    }
    
    public List<Company> fingByID (int id) {
    	List<Company> result;
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", id, Types.INTEGER);
    	result = jdbc.query(QUERY_FINDBYID, parameters, companyMapper);
    	return result;
    }
    

    public List<Company> findAllByPage (Page page) {
    	List<Company> result;
    	
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("limit", page.getPageLength(), Types.INTEGER);
    	parameters.addValue("offset", page.getOffset(), Types.INTEGER);
    	
    	result = jdbc.query(QUERY_FINDBYPAGE_COMPANY, parameters, companyMapper);
    	
    	return result;
    }
    

    
    public int countEntry() {
    	int result = 0;
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	result = jdbc.queryForObject(QUERY_COUNT_COMPANY, parameters, Integer.class);
    	return result;
    }
    
    @Transactional
    public void deleteCompany (Company company) {
    	
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	parameters.addValue("id", company.getId(), Types.INTEGER);
    	jdbc.update(QUERY_DELETE_COMPUTER, parameters);
    	jdbc.update(QUERY_DELETE_COMPANY, parameters);
    	
    }
}
