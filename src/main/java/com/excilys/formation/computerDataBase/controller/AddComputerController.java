package com.excilys.formation.computerDataBase.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.computerDataBase.model.DTO.CompanyDTO;
import com.excilys.formation.computerDataBase.service.AddComputerService;

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
