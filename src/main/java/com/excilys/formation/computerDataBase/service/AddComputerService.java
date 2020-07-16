package com.excilys.formation.computerDataBase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private CompanyDAO companyDAO;

	public AddComputerService() {
	}
	
	public boolean addComputer (String name, String introduced, String discontinued, String compagnyId) {
		ComputerDTO computerDTO = new ComputerDTO("0", name, introduced, discontinued, new CompanyDTO(compagnyId,"null"));
		Computer computer = ComputerDTOMapper.dtoToComputer(computerDTO);
		return computerDAO.add(computer.getName(),
				computer.getIntroduced(),
				computer.getDiscontinued(),
				computer.getCompanyId());
	}
	
	public List<CompanyDTO> getListCompany () {		
		System.out.println("coucou");
		return CompanyDTOMapper.companyListToDTOList(companyDAO.findCompany());
	}
}
