package com.excilys.formation.computerDataBase.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.formation.computerDataBase.mapper.CompanyDTOMapper;
import com.excilys.formation.computerDataBase.mapper.ComputerDTOMapper;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.DTO.CompanyDTO;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.persistence.CompanyDAO;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;


@Service
public class AddComputerService {
	private ComputerDAO computerDAO;
	private CompanyDAO companyDAO;

	public AddComputerService() {
		this.computerDAO = new ComputerDAO();
		this.companyDAO = new CompanyDAO();
	}
	
	public void addComputer (String name, String introduced, String discontinued, String compagnyId) {
		ComputerDTO computerDTO = new ComputerDTO("0", name, introduced, discontinued, new CompanyDTO(compagnyId,"null"));
		Computer computer = ComputerDTOMapper.dtoToComputer(computerDTO);
		computerDAO.add(computer.getName(),
				computer.getIntroduced(),
				computer.getDiscontinued(),
				computer.getCompanyId());
	}
	
	public List<CompanyDTO> getListCompany () {		
		System.out.println("coucou");
		return CompanyDTOMapper.companyListToDTOList(companyDAO.findCompany());
	}
}
