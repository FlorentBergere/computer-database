package com.excilys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.CompanyDTO;
import com.excilys.service.AddComputerService;

/**
 * Servlet implementation class AddComputer
 */
@Controller
public class AddComputerController {
	
	@Autowired
	private AddComputerService addComputerService;


    @GetMapping("addComputer")
    public String getFormAddComputer(ModelMap map){
    	List<CompanyDTO>companyDTOcollection = addComputerService.getListCompany();
    	map.put("listCompany", companyDTOcollection);
    
    	return "addComputer";
    }
	
    @PostMapping("addComputer")
   	public String addComputer (
   			@RequestParam("computerName") String name,
   			@RequestParam("introduced") String introduced,
   			@RequestParam("discontinued") String discontinued,
   			@RequestParam("companyId") String companyId,
   			ModelMap map) {
    	
    	boolean addSuccess = addComputerService.addComputer(name, introduced, discontinued, companyId);
    	map.put("addSuccess", addSuccess);
    	map.put("addButtonClick", true);
    	
    	return "addComputer";
    	
    }

}
