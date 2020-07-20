package com.excilys.formation.computerDataBase.persistence;

import java.sql.Types;
import java.util.List;

import javax.management.Query;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.computerDataBase.mapper.CompanyMapper;
import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.Page;
import com.excilys.formation.computerDataBase.service.ConnectionFactory;
import com.zaxxer.hikari.HikariDataSource;

@Repository
@Transactional
public class CompanyDAO {
	@Autowired
	ConnectionFactory connectionFactory;
	
    @Autowired
	HikariDataSource hikariDataSource;
    
    @Autowired
    CompanyMapper companyMapper;
    
    @Autowired
    NamedParameterJdbcTemplate jdbc;
    
    @Autowired
    SessionFactory sessionFactory;
    
    private final static String QUERYHQL_FIND_COMPANY = "FROM Company";
    private final static String QUERYHQL_FINDBYID = "FROM Company company where company.id = :id";
    private final static String QUERYHQL_COUNT_COMPANY = "SELECT count(company.id) FROM Company company";
    private final static String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE company_id = :id";
    private final static String QUERYHQL_DELETE_COMPUTER = "DELETE FROM Computer computer WHERE computer.company_id=:id";
    private final static String QUERY_DELETE_COMPANY = "DELETE FROM company WHERE id = :id";
    private final static String QUERYHQL_DELETE_COMPANY = "DELETE FROM Company company WHERE company.id=:id";
    
    
        
    public List<Company> findCompany (){
		TypedQuery<Company> query = sessionFactory.getCurrentSession().createQuery(QUERYHQL_FIND_COMPANY,Company.class);
		return query.getResultList();
    }
    
    
    public List<Company> fingByID (int id) {
    	TypedQuery<Company> query = sessionFactory.getCurrentSession().createQuery(QUERYHQL_FINDBYID,Company.class).setParameter("id", id);
    	return query.getResultList();
    }
    
    
    public List<Company> findAllByPage (Page page) {
		TypedQuery<Company> query = sessionFactory.getCurrentSession().createQuery(QUERYHQL_FIND_COMPANY,Company.class);
		query.setFirstResult(page.getOffset());
		query.setMaxResults(page.getPageLength());
		return query.getResultList();
    }
    

    
    public int countEntry() {
    	TypedQuery<Long> query = sessionFactory.getCurrentSession().createQuery(QUERYHQL_COUNT_COMPANY,Long.class);
    	return query.getSingleResult().intValue();
    }
    
    
    public void deleteCompany (Company company) {
    	
//    	MapSqlParameterSource parameters = new MapSqlParameterSource();
//    	parameters.addValue("id", company.getId(), Types.INTEGER);
//    	jdbc.update(QUERY_DELETE_COMPUTER, parameters);
//    	jdbc.update(QUERY_DELETE_COMPANY, parameters);
    	TypedQuery<Computer> queryDeleteComputer = sessionFactory.getCurrentSession().createQuery(QUERY_DELETE_COMPUTER,Computer.class).setParameter("id",company.getId());
    	TypedQuery<Company> queryDeleteCompany = sessionFactory.getCurrentSession().createQuery(QUERY_DELETE_COMPANY,Company.class).setParameter("id",company.getId());
    	queryDeleteComputer.executeUpdate();
    	queryDeleteCompany.executeUpdate();
    	
    }
}
