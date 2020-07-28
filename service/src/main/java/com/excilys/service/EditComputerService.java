package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;

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
		return computerDAO.update(computer);
	}
	
}
