package com.excilys.formation.computerDataBase.persistence;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.time.LocalDate;
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
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.service.ConnectionFactory;
import com.excilys.formation.computerDataBase.configuration.SpringConfig;
import com.excilys.formation.computerDataBase.mapper.DateMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class ComputerDAOTest extends DBTestCase{
	@Autowired
	private ConnectionFactory connectionFactory;
	@Autowired
	private ComputerDAO computerDAO;
	
	
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
	public void testAdd() {
		int id = 1000;
		String name = "computerName";
		LocalDate introduced = DateMapper.stringToLocalDate("1999-12-01");
		LocalDate discontinued = DateMapper.stringToLocalDate("2014-09-07");
		Company company = new Company(3,"RCA");
		
		
		Computer computer = new Computer(id,name,introduced,discontinued,new Company(3,"RCA"));
		computerDAO.add(name, introduced, discontinued, company.getId());
		System.out.println(computerDAO.findAll());
		Computer result = computerDAO.fingByID(id).get(0);
		assertEquals(computer, result);
	}
	
	@Test
	public void testFindByIdWhenNotPresent() {
		int id = 6;
		List<Computer> result = computerDAO.fingByID(id);
		assertTrue(result.isEmpty());
	}

}
