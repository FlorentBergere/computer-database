package com.excilys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;



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
		return companyCollection.stream().map(company -> CompanyToDTO(company)).collect(Collectors.toList());
	}
}
