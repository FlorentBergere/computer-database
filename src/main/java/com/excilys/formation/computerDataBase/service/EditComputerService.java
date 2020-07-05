package com.excilys.formation.computerDataBase.service;

import java.util.List;

import com.excilys.formation.computerDataBase.mapper.CompanyDTOMapper;
import com.excilys.formation.computerDataBase.mapper.ComputerDTOMapper;
import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.DTO.CompanyDTO;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.persistence.CompanyDAO;
import com.excilys.formation.computerDataBase.persistence.ComputerDAO;

public class EditComputerService {
	CompanyDAO companyDAO;
	ComputerDAO computerDAO;
	
	public EditComputerService() {
		this.companyDAO = new CompanyDAO();
		this.computerDAO = new ComputerDAO();
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
	
	public void editComputer (ComputerDTO computerDTO) {
		Computer computer = ComputerDTOMapper.dtoToComputer(computerDTO);
		computerDAO.update(computer.getId(),
				computer.getName(),
				computer.getIntroduced(),
				computer.getDiscontinued(),
				computer.getCompanyId());
	}
	
}