package com.excilys.persistance;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Page;

@Repository
@Transactional
public class CompanyDAO {  
    @Autowired
    SessionFactory sessionFactory;
    
    private final static String QUERYHQL_FIND_COMPANY = "FROM Company";
    private final static String QUERYHQL_FINDBYID = "FROM Company company where company.id = :id";
    private final static String QUERYHQL_COUNT_COMPANY = "SELECT count(company.id) FROM Company company";
    private final static String QUERYHQL_DELETE_COMPUTER = "DELETE FROM Computer computer WHERE computer.company=:company";
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
    
    
//  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//  session.beginTransaction();
//  session.save(foo); //Here you have the magic
//  session.getTransaction().commit();
//  session.close();
    @SuppressWarnings("unchecked")
    public void deleteCompany (Company company) {   	
		TypedQuery<Computer> queryDeleteComputer = sessionFactory.getCurrentSession().createQuery(QUERYHQL_DELETE_COMPUTER).setParameter("company",company);
    	TypedQuery<Company> queryDeleteCompany = sessionFactory.getCurrentSession().createQuery(QUERYHQL_DELETE_COMPANY).setParameter("id",company.getId());
    	queryDeleteComputer.executeUpdate();
    	queryDeleteCompany.executeUpdate();
    	
    }
}
