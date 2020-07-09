package com.excilys.formation.computerDataBase.persistence;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.computerDataBase.service.ConnectionFactory;
import com.mysql.cj.xdevapi.Result;
import com.excilys.formation.computerDataBase.model.Company;


public class CompanyDAOTest extends DBTestCase{
	private ConnectionFactory connectionFactory;
	private CompanyDAO companyDAO;
	
	private static List<Company> expectedListAll = Arrays.asList(	new Company(1, "Apple Inc."),
																	new Company(2, "Thinking Machines"),
																	new Company(3, "RCA"));
	
	
	
    public CompanyDAOTest(String name) {
    	super(name);
//        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver" );
//        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/computer-database-db-test?serverTimezone=UTC" );
//        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "testcdb" );
//        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "test" );
        //
        
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:computer-database-db-test;DB_CLOSE_DELAY=-1" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "test" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
    }

    
    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset.xml"));
    }
   
//    @Override
//	protected DatabaseOperation getSetUpOperation() throws Exception {
//        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
//        return DatabaseOperation.NONE;
//	}
//    
//    @Override
//	protected DatabaseOperation getTearDownOperation() throws Exception {
//		return DatabaseOperation.NONE;
//	}
    
	@SuppressWarnings({ "static-access"})
	@Before
	public void setUp() throws Exception {
		connectionFactory = ConnectionFactory.getInstance();
		companyDAO = new CompanyDAO();
		getSetUpOperation().execute(new DatabaseConnection(connectionFactory.getConnection()), getDataSet());
	}

	@Test
	public void testFinDCompany() {
		List<Company> result = companyDAO.findCompany();
		System.out.println(result);
		assertEquals(expectedListAll, result);
	}

	
}
