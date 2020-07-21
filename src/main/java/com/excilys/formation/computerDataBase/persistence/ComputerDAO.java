package com.excilys.formation.computerDataBase.persistence;

import java.sql.Types;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional
public class ComputerDAO {
	@Autowired
	ConnectionFactory connectionFactory;
	
    @Autowired
	HikariDataSource hikariDataSource;
    
    @Autowired
    ComputerMapper computerMapper;
    
    @Autowired
    NamedParameterJdbcTemplate jdbc;
    
    @Autowired
    SessionFactory sessionFactory;
    
    private final static String QUERY_FIND_COMPUTER = "SELECT computer.id, computer.name as computerName, introduced, discontinued, company.id AS company_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id=company.id";
    private final static String QUERY_WHERE_ATTRIBUTE_LIKE = " WHERE computer.name LIKE '%:like%'";
    private final static String QUERY_ORDER_BY = " ORDER BY :attribute ";
    private final static String QUERY_LIMIT_OFFSET = " LIMIT :limit OFFSET :offset" ;
    
    
    private final static String QUERYHQL_FIND_COMPUTER = "FROM Computer computer";
    private final static String QUERYHQL_FINDBYID = "FROM Computer computer where computer.id = :id";
    private final static String QUERYHQL_DELETE_COMPUTER = "DELETE FROM Computer computer WHERE computer.id=:id";
    private final static String QUERYHQL_COUNT_COMPUTER = "SELECT count(computer.id) FROM Computer computer";
    private final static String QUERYHQL_UPDATE = "UPDATE Computer computer SET  computer.name = :name, computer.introduced = :introduced, computer.discontinued = :discontinued, computer.company = :company where computer.id = :id";
    private final static String QUERYHQL_WHERE_ATTRIBUTE_LIKE = " WHERE computer.name LIKE :like";
    private final static String QUERYHQL_ORDER_BY = " ORDER BY :attribute ";

    
    public List<Computer> findAll () { 	
		TypedQuery<Computer> query = sessionFactory.getCurrentSession().createQuery(QUERYHQL_FIND_COMPUTER,Computer.class);
		return query.getResultList();
    }
    
    public List<Computer> fingByID (int id) {
    	TypedQuery<Computer> query = sessionFactory.getCurrentSession().createQuery(QUERYHQL_FINDBYID,Computer.class).setParameter("id", id);
    	return query.getResultList();
    }
    
    
    public boolean add (Computer computer) {
    	sessionFactory.getCurrentSession().save(computer);
    	return true;
    }
    
    
    @SuppressWarnings("unchecked")
	public boolean update (Computer computer) {
    	boolean result = false;
    	TypedQuery<Computer> query = sessionFactory.getCurrentSession().createQuery(QUERYHQL_UPDATE)
				.setParameter("name", computer.getName())
				.setParameter("introduced", computer.getIntroduced())
				.setParameter("discontinued", computer.getDiscontinued())
				.setParameter("company", computer.getCompany())
				.setParameter("id",computer.getId());
    	if (query.executeUpdate() > 0) {
    		result = true;    		
    	}
    	return result;
    }
    
    @SuppressWarnings("unchecked")
    public void delete (int id) {
		TypedQuery<Computer> queryDeleteComputer = sessionFactory.getCurrentSession().createQuery(QUERYHQL_DELETE_COMPUTER).setParameter("id",id);
    	queryDeleteComputer.executeUpdate();
    }
    
    
//    public List<Computer> findAllByPage (Page page) {
//    	List<Computer> result =null;
//    	String query = QUERY_FIND_COMPUTER;
//    	if( page.getSearch() != null ) {
//    		query += QUERY_WHERE_ATTRIBUTE_LIKE.replace(":like", page.getSearch());
//    	}
//    	if( page.getAttributeToOrder() != null ) {
//    		query += QUERY_ORDER_BY.replace(":attribute", page.getAttributeToOrder());
//    		query += page.getCurrentOrder();
//    	}
//    	query += QUERY_LIMIT_OFFSET
//    			.replace(":limit", Integer.valueOf(page.getPageLength()).toString())
//    			.replace(":offset", Integer.valueOf(page.getOffset()).toString());
//    	
//    	result = jdbc.query(query, computerMapper);
//    	return result;
//    }
    
    public List<Computer> findAllByPage (Page page){
    	String stringQuery = QUERYHQL_FIND_COMPUTER;
    	if( page.getSearch() != null && !page.getSearch().isEmpty()) {
    		stringQuery += QUERYHQL_WHERE_ATTRIBUTE_LIKE.replace(":like", page.getSearch());
    		System.out.println("coucou" +page.getSearch());
    	}
    	if( page.getAttributeToOrder() != null ) {
    		stringQuery += QUERYHQL_ORDER_BY.replace(":attribute", page.getAttributeToOrder());
    		stringQuery += page.getCurrentOrder();
    	}
    	TypedQuery<Computer> query = sessionFactory.getCurrentSession().createQuery(stringQuery,Computer.class)
    			.setFirstResult(page.getOffset())
    			.setMaxResults(page.getPageLength());
    	return query.getResultList();
    }

    
    public int countEntry (){
    	TypedQuery<Long> query = sessionFactory.getCurrentSession().createQuery(QUERYHQL_COUNT_COMPUTER,Long.class);
    	return query.getSingleResult().intValue();
    }
}
