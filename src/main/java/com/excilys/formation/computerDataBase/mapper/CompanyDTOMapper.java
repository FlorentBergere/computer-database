package com.excilys.formation.computerDataBase.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.DTO.CompanyDTO;

public class CompanyDTOMapper {
	public static Company dtoToCompany (CompanyDTO companyDTO) {
		return new Company(
			Integer.valueOf(companyDTO.getId()),
			companyDTO.getName());

	}
	
	public static CompanyDTO CompanyToDTO (Company company) {
		return new CompanyDTO(
			Integer.valueOf(company.getId()).toString(),
			company.getName());

	}
	
	public static List<CompanyDTO> companyListToDTOList (List<Company> companyCollection) {
		List<CompanyDTO> result = new ArrayList<CompanyDTO>();
		
		for(Company company : companyCollection) {
			result.add(CompanyToDTO(company));
		}
		
		return result;
	}
}
