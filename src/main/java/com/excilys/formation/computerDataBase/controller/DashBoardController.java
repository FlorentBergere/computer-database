package com.excilys.formation.computerDataBase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.service.DashBoardService;


@Controller
public class DashBoardController {

	@Autowired
	private DashBoardService dashBoardService;
	
	private int numberMaxPage;
	private List<Integer> listPage;
    private List<ComputerDTO> computerDTOCollection;
       

    @GetMapping("dashboard")
	public void dashboard(
			@RequestParam(value = "pageNumber ", defaultValue = "0") int pageNumber,
			@RequestParam(value = "nbEntryPerPage ", defaultValue = "10") int nbEntryPerPage,
			@RequestParam(value = "search", required = false) String search,
    		@RequestParam(value = "atribute", required = false) Computer.atributes atribute,
    		ModelMap map){
    	
    	computerDTOCollection = dashBoardService.findAllByPage(nbEntryPerPage,pageNumber);
    	numberMaxPage = dashBoardService.getNumberMaxPage();
    	listPage = dashBoardService.getListPage();
    	map.put("computerDTOCollection", computerDTOCollection);
    	map.put("nbComputer", dashBoardService.countComputer());
    	map.put("nbPage", numberMaxPage);
    	map.put("listPage", listPage);
    	map.put("pageNumber", pageNumber);
    	map.put("nbEntryPerPage", nbEntryPerPage);
    	
    }


	
	
//		atribute = ComputerMapper.parseAtribute((request.getParameter("orderBy")));
//		
//		search = request.getParameter("search");
//		if(atribute == null) {
//			if (search != null && !search.isEmpty()) {
//				computerDTOCollection = dashBoardService.findAllByPage(nbEntryPerPage,pageNumber,search);
//			}else {
//				computerDTOCollection = dashBoardService.findAllByPage(nbEntryPerPage,pageNumber);
//			}
//		}else{
//			if (search != null && !search.isEmpty()) {
//				computerDTOCollection = dashBoardService.findAllByPage(nbEntryPerPage,pageNumber,search,atribute);
//			}else {
//				computerDTOCollection = dashBoardService.findAllByPage(nbEntryPerPage,pageNumber,atribute);
//			}
//		}

//		
//		request.getRequestDispatcher("views/dashboard.jsp").forward(request,response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		
//		String idsToDelete = null;
//		idsToDelete = request.getParameter("selection");
//		if (idsToDelete != null && !idsToDelete.isEmpty() ) {
//			dashBoardService.delete(idsToDelete);
//		}
//		
//		
//		search = request.getParameter("search");
//		doGet(request, response);
//	}

}
