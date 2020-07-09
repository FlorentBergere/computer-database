package com.excilys.formation.computerDataBase.persistence;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.formation.computerDataBase.service.ConnectionFactory;
import com.excilys.formation.computerDataBase.configuration.SpringConfig;
import com.excilys.formation.computerDataBase.model.Company;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class CompanyDAOTest extends DBTestCase{
	@Autowired
	private ConnectionFactory connectionFactory;
	@Autowired
	private CompanyDAO companyDAO;
	
	private static List<Company> expectedListAll = Arrays.asList(	
			new Company(1, "Apple Inc."),
			new Company(2, "Thinking Machines"),
			new Company(3, "RCA"));
	
	
	
    public CompanyDAOTest() {
    }

    
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
		getSetUpOperation().execute(new DatabaseConnection(connectionFactory.getConnection()), getDataSet());
	}

	@Test
	public void testFinDCompany() {
		List<Company> result = companyDAO.findCompany();
		System.out.println(result);
		assertEquals(expectedListAll, result);
	}

	
}
