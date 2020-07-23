package com.excilys.persistence;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.config.HibernateConfig;
import com.excilys.config.SpringConfig;
import com.excilys.model.Company;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, HibernateConfig.class})
@Transactional
public class CompanyDAOTest extends DBTestCase{

	
	@Autowired
	private ConnectionFactory connectionFactory;
	@Autowired
	private CompanyDAO companyDAO;
	

	
	private static List<Company> expectedListAll = Arrays.asList(	
			new Company(1, "Apple Inc."),
			new Company(2, "Thinking Machines"),
			new Company(3, "RCA"));
	

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset.xml"));
    }
   
    @Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
	}
    
    @Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
    
	
	@Before
	public void setUp() throws Exception {
		try(Connection connection = connectionFactory.getConnection()){
			getSetUpOperation().execute(new DatabaseConnection(connection), getDataSet());
		}
	}

	@Test
	public void testFinDCompany() {
		List<Company> result = companyDAO.findCompany();
		assertEquals(expectedListAll, result);
	}
	
	@Test 
	public void testFindById () {
		List<Company> result = companyDAO.fingByID(1);
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(new Company(1, "Apple Inc."), result.get(0));
	}
	
	@Test
	public void testDeleteCompanyWithNoComputerLink() {
		List<Company> result = null;
		
		result = companyDAO.fingByID(3);
		assertNotNull(result);
		assertEquals(1, result.size());
		
		companyDAO.deleteCompany(new Company(3, "RCA"));
		
		result = companyDAO.fingByID(3);
		assertNotNull(result);
		assertEquals(0, result.size());
	}
	
	@Test
	public void testDeleteCompanyWithComputerLink() {
		List<Company> result = null;
		
		result = companyDAO.fingByID(1);
		assertNotNull(result);
		assertEquals(1, result.size());
		
		companyDAO.deleteCompany(new Company(1, "Apple Inc."));
		
		result = companyDAO.fingByID(1);
		assertNotNull(result);
		assertEquals(0, result.size());
	}
	
	@Test
	public void testCountEntry () {
		int result = companyDAO.countEntry();
		assertEquals(3, result);
	}
		
}
