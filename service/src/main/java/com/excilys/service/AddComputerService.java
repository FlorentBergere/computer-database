package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Computer;
import com.excilys.persistance.CompanyDAO;
import com.excilys.persistance.ComputerDAO;


@Service
public class AddComputerService {
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private CompanyDAO companyDAO;

	public AddComputerService() {
	}
	
	public boolean addComputer (String name, String introduced, String discontinued, String compagnyId) {
		ComputerDTO computerDTO = new ComputerDTO("0", name, introduced, discontinued, new CompanyDTO(compagnyId,"null"));
		Computer computer = ComputerDTOMapper.dtoToComputer(computerDTO);
		return computerDAO.add(computer);
	}
	
	public List<CompanyDTO> getListCompany () {		
		return CompanyDTOMapper.companyListToDTOList(companyDAO.findCompany());
	}
}
