package com.excilys.formation.computerDataBase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.computerDataBase.mapper.CompanyDTOMapper;
import com.excilys.formation.computerDataBase.mapper.ComputerDTOMapper;
import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.DTO.CompanyDTO;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.persistence.CompanyDAO;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

@Service
public class EditComputerService {
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private ComputerDAO computerDAO;
	
	public EditComputerService() {
	}
	
	public List<CompanyDTO> getListCompany () {		
		return CompanyDTOMapper.companyListToDTOList(companyDAO.findCompany());
	}
	
	public CompanyDTO findCompanyById (String id) {
		CompanyDTO result = null;
		Company company = null;
		if (id == "0") {
			company = new Company(0, null);
		}else {
			company = companyDAO.fingByID(Integer.valueOf(id)).get(0);
		}

		result = CompanyDTOMapper.CompanyToDTO(company);
		return result;
	}
	
	public boolean editComputer (ComputerDTO computerDTO) {
		Computer computer = ComputerDTOMapper.dtoToComputer(computerDTO);
		return computerDAO.update(computer.getId(),
				computer.getName(),
				computer.getIntroduced(),
				computer.getDiscontinued(),
				computer.getCompanyId());
	}
	
}
