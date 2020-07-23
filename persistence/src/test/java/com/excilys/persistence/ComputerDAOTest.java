package com.excilys.persistence;

import java.io.FileInputStream;
import java.sql.Connection;
import java.time.LocalDate;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.config.HibernateConfig;
import com.excilys.config.SpringConfig;
import com.excilys.mapper.DateMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, HibernateConfig.class})
@Transactional
public class ComputerDAOTest extends DBTestCase{
	@Autowired
	private ConnectionFactory connectionFactory;
	@Autowired
	private ComputerDAO computerDAO;
	
	private static List<Computer> expectedListAll = Arrays.asList(	
			new Computer(1,"MacBook Pro 15.4 inch", null, null, new Company(1, "Apple Inc.")),
			new Computer(2,"CM-2a" , null, null, new Company(2, "Thinking Machines")),
			new Computer(3,"CM-200",DateMapper.stringToLocalDate("1999-09-06"),DateMapper.stringToLocalDate("2000-03-03"),new Company(2, "Thinking Machines")),
			new Computer(4,"CM-5e",null,null,new Company(2, "Thinking Machines")),
			new Computer(5,"CM-5",DateMapper.stringToLocalDate("1991-01-01"),null,new Company(2, "Thinking Machines"))
			);
	
	
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
	public void testFindAll() {
		assertEquals(expectedListAll, computerDAO.findAll());
	}
	
	@Test
	public void testFindByIdWhenNotPresent() {
		int id = 6;
		List<Computer> result = computerDAO.fingByID(id);
		assertTrue(result.isEmpty());
	}

	
	@Test
	public void testAdd() {
		int id = 6;
		String name = "computerName";
		LocalDate introduced = DateMapper.stringToLocalDate("1999-12-01");
		LocalDate discontinued = DateMapper.stringToLocalDate("2014-09-07");
		Company company = new Company(3,"RCA");
		
		
		Computer computer = new Computer(id,name,introduced,discontinued,company);
		computerDAO.add(computer);
		Computer result = computerDAO.fingByID(id).get(0);
		assertEquals(computer, result);
	}
	
	
	@Test 
	public void testCountEntry () {
		assertEquals(5, computerDAO.countEntry());
	}

	@Test
	public void testDelete () {
		List<Computer> result ;
		result = computerDAO.fingByID(3);
		assertTrue(!result.isEmpty());
		computerDAO.delete(3);
		result = computerDAO.fingByID(3);
		assertTrue(result.isEmpty());
	}
	
	@Test 
	public void testUpdate () {
		Computer computer = computerDAO.fingByID(1).get(0);
		computer.setCompany(new Company(3,"RCA"));
		computerDAO.update(computer);
		Computer result = computerDAO.fingByID(1).get(0);
		assertEquals(computer, result);
	}
}
