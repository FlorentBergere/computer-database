package com.excilys.formation.computerDataBase.validator;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.net.ConnectException;
import java.sql.Connection;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.formation.computerDataBase.configuration.HibernateConfig;
import com.excilys.formation.computerDataBase.configuration.SpringConfig;
import com.excilys.formation.computerDataBase.mapper.DateMapper;
import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.service.ConnectionFactory;
import com.excilys.formation.computerDataBase.validator.exception.ValidatorException;
import com.excilys.formation.computerDataBase.validator.exception.company.CompanyException;
import com.excilys.formation.computerDataBase.validator.exception.computer.CompanyDoesNotExistException;
import com.excilys.formation.computerDataBase.validator.exception.computer.ComputerException;
import com.excilys.formation.computerDataBase.validator.exception.computer.EmptyNameException;
import com.excilys.formation.computerDataBase.validator.exception.computer.IntroducedDateAfeterDiscontinuedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class,HibernateConfig.class})
public class ComputerValidatorTest extends DBTestCase{

	@Autowired
	ConnectionFactory connectionFactory;
	
	@Autowired
	ComputerValidator computerValidator;
	
	
	
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
			try(Connection connexion = connectionFactory.getConnection()){
				getSetUpOperation().execute(new DatabaseConnection(connexion), getDataSet());
			}
			
		}
	

	@Test(expected = EmptyNameException.class)
	public void test_ComputerWithEmptyName_validateComputer_EmptyNameException() throws ValidatorException{
		//given
		Computer computer = new Computer(4,"",null,null,new Company(1,"test"));
		
		//when
		computerValidator.validateComputer(computer);
		
		//then
		//throw EmptyNameException
	}
	
	@Test(expected = IntroducedDateAfeterDiscontinuedException.class)
	public void test_ComputerWithIntroducedAfterDiscontinued_validateComputer_IntroducedDateAfeterDiscontinuedException() throws ValidatorException{
		//given
		Computer computer = new Computer(4,"test",DateMapper.stringToLocalDate("2019-06-21"),DateMapper.stringToLocalDate("2019-06-20"),new Company(1,"test"));
		
		//when
		computerValidator.validateComputer(computer);
		
		//then
		//throw EmptyNameException
	}
	
	@Test()
	public void test_ComputerWithValidCompanyInBase_validateComputer_CompanyDoesNotExistException() throws ValidatorException{
		//given
		Computer computer = new Computer(4,"test",null,null,new Company(3,"RCA"));
		
		//when
		computerValidator.validateComputer(computer);
		
		//then
		//throw EmptyNameException
	}
	
	@Test(expected = CompanyDoesNotExistException.class)
	public void test_ComputerWithCompanyWithIdNotMatchingName_validateComputer_CompanyDoesNotExistException() throws ValidatorException{
		//given
		Computer computer = new Computer(4,"test",null,null,new Company(3,"RBA"));
		
		//when
		computerValidator.validateComputer(computer);
		
		//then
		//throw EmptyNameException
	}
	


}
