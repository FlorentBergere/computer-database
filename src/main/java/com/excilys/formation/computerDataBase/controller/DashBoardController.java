package com.excilys.formation.computerDataBase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.computerDataBase.mapper.ComputerMapper;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.Page;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.service.DashBoardService;


@Controller
public class DashBoardController {

	@Autowired
	private DashBoardService dashBoardService;
	
	private Page pageComputerDTO = null;
	private int numberMaxPage;
	private List<Integer> listPage;
    private List<ComputerDTO> computerDTOCollection;
       

    @GetMapping("dashboard")
	public String dashboard(
			@RequestParam(value = "nbEntryPerPage", defaultValue = "10") int pageLength,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "search", required = false) String search,
    		@RequestParam(value = "atribute", required = false) String atribute,
    		ModelMap map){
    	
    	pageComputerDTO = dashBoardService.initPage();
    	dashBoardService.setPageLength(pageLength);
    	dashBoardService.goTo(pageNumber);
    	dashBoardService.setSearch(search);
    	dashBoardService.setAttributeToOrder(ComputerMapper.parseAtribute(atribute));
    	
    	computerDTOCollection = dashBoardService.findAllByPage();
    	numberMaxPage = pageComputerDTO.getNbPage();
    	listPage = pageComputerDTO.getListPage();
    	map.put("computerDTOCollection", computerDTOCollection);
    	map.put("nbComputer", dashBoardService.countComputer());
    	map.put("nbPage", numberMaxPage);
    	map.put("listPage", listPage);
    	map.put("pageNumber", pageNumber);
    	map.put("nbEntryPerPage", pageLength);
    	
    	return "dashboard";
    }

    @PostMapping("dashboard")
	public String deleteComputer (
			@RequestParam(value = "selection", defaultValue = "") String idsToDelete,
			ModelMap map) {
    	dashBoardService.delete(idsToDelete);
    	return "redirect:dashboard?"
    			+ "nbEntryPerPage="+pageComputerDTO.getPageLength() + "&" 
    			+ "pageNumber="+pageComputerDTO.getNbPage() + "&" 
    			+ "search="+ pageComputerDTO.getSearch() + "&" 
    			+ "atribute=" + pageComputerDTO.getAttributeToOrder();
    }

	
	
}
