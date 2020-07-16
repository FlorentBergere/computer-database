package com.excilys.formation.computerDataBase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.excilys.formation.computerDataBase.model.DTO.CompanyDTO;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.service.EditComputerService;

/**
 * Servlet implementation class EditComputer
 */

@Controller
public class EditComputerController  {
	
	@Autowired
	private EditComputerService editComputerService;
       
	
    @GetMapping("editComputer")
	public String geteditComputer(
			@RequestParam("computerId") String computerId,
			@RequestParam("computerName") String name,
			@RequestParam("introduced") String introduced,
			@RequestParam("discontinued") String discontinued,
			@RequestParam("companyId") String companyId,
			ModelMap map) {
    	List<CompanyDTO>companyDTOcollection = editComputerService.getListCompany();
    	map.put("listCompany", companyDTOcollection);
    	
    	map.put("computerId", computerId);
    	map.put("computerName", name);
    	map.put("introduced", introduced);
    	map.put("discontinued", discontinued);
    	map.put("companyId", companyId);
    	
    
    	return "editComputer";
    }
    
    
    @PostMapping("editComputer")
   	public String editComputer (
			@RequestParam("computerId") String computerId,
			@RequestParam("computerName") String name,
			@RequestParam("introduced") String introduced,
			@RequestParam("discontinued") String discontinued,
			@RequestParam("companyId") String companyId,
			ModelMap map) {
    	
    	CompanyDTO companyDTO = editComputerService.findCompanyById(companyId);	
		ComputerDTO computerDTO = new ComputerDTO(computerId, name, introduced, discontinued, companyDTO);
    	boolean editSuccess = editComputerService.editComputer(computerDTO);
    	
    	map.put("editSuccess", editSuccess);
    	map.put("editButtonClick", true);
    	
    	return "editComputer";
    	
    }

}
