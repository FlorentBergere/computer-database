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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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
	public void editComputer() {
    	
    }
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public EditComputerController() {
//        super();
//    }
//    
//
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		
//		String computerId = request.getParameter("computerId");
//		String computerName = request.getParameter("computerName");
//		String introduced = request.getParameter("introduced");
//		String discontinued = request.getParameter("discontinued");
//		String companyId = request.getParameter("companyId");
//		
//
//		request.setAttribute("computerId",computerId);
//		request.setAttribute("computerName",computerName);
//		request.setAttribute("introduced",introduced);
//		request.setAttribute("discontinued",discontinued);
//		request.setAttribute("companyId",companyId);
//		
//		List<CompanyDTO> companyDTOcollection = editComputerService.getListCompany();
//		request.setAttribute("listCompany", companyDTOcollection);
//				
//		System.out.println(computerId+"#"+computerName+"#"+introduced+"#"+discontinued+"#"+companyId);
//		
//		request.getRequestDispatcher("views/editComputer.jsp").forward(request,response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		String id = request.getParameter("computerId");
//		String name = request.getParameter("computerName");
//		String introduced = request.getParameter("introduced");
//		String discontinued = request.getParameter("discontinued");
//		String compagnyId = request.getParameter("companyId");
//		
//		
//		CompanyDTO companyDTO = editComputerService.findCompanyById(compagnyId);	
//		ComputerDTO computerDTO = new ComputerDTO(id, name, introduced, discontinued, companyDTO);
//		
//		editComputerService.editComputer(computerDTO);
//		
//		doGet(request, response);
//	}

}
