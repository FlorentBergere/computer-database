package com.excilys.formation.computerDataBase.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.excilys.formation.computerDataBase.model.Company;
import com.excilys.formation.computerDataBase.model.DTO.CompanyDTO;

public class CompanyDTOMapper {
	public static Company dtoToCompany (CompanyDTO companyDTO) {
		return new Company(
			//companyDTO.getId() == "0" ? null : Integer.valueOf(companyDTO.getId()),
			Integer.valueOf(companyDTO.getId()),
			companyDTO.getName());

	}
	
	public static CompanyDTO CompanyToDTO (Company company) {
		return new CompanyDTO(
			Integer.valueOf(company.getId()).toString(),
			company.getName());

	}
	
	public static List<CompanyDTO> companyListToDTOList (List<Company> companyCollection) {
		return companyCollection.stream().map(company -> CompanyToDTO(company)).collect(Collectors.toList());
	}
}
