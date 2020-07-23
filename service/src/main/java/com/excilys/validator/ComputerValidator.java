package com.excilys.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistance.CompanyDAO;
import com.excilys.validator.exception.company.CompanyException;
import com.excilys.validator.exception.computer.CompanyDoesNotExistException;
import com.excilys.validator.exception.computer.ComputerException;
import com.excilys.validator.exception.computer.EmptyNameException;
import com.excilys.validator.exception.computer.IntroducedDateAfeterDiscontinuedException;

@Component
public class ComputerValidator {
	
	@Autowired
	CompanyValidator companyValidator;
	
	@Autowired
	CompanyDAO companyDao;
	
	private void nameEmpty(Computer computer) throws EmptyNameException {
		if (computer.getName() == null || computer.getName().isEmpty()){
			throw new EmptyNameException();
		}
	}
	
	private void introducedBeforeDiscontunued (Computer computer)  throws IntroducedDateAfeterDiscontinuedException {
		if (computer.getIntroduced() !=null &&  computer.getDiscontinued() != null && computer.getIntroduced().isAfter(computer.getDiscontinued()) ) {
			throw new IntroducedDateAfeterDiscontinuedException();
		}
	}
	
	private void companyExist (Computer computer) throws CompanyDoesNotExistException {
		List<Company> company = companyDao.fingByID(computer.getCompanyId());
		if ( company.isEmpty() ) {
			throw new CompanyDoesNotExistException();
		}else if ( !company.get(0).equals(computer.getCompany()) ) {
			throw new CompanyDoesNotExistException();
		}
	}
	
	public void validateComputer(Computer computer) throws ComputerException, CompanyException{
		nameEmpty(computer);
		introducedBeforeDiscontunued(computer);
		companyValidator.validateCompany(computer.getCompany());
		companyExist(computer);
		
	}
}
