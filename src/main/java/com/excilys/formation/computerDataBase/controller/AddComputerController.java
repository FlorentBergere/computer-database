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
import com.excilys.formation.computerDataBase.service.AddComputerService;

/**
 * Servlet implementation class AddComputer
 */
@Controller
public class AddComputerController {
	
	@Autowired
	private AddComputerService addComputerService;
	String name;
	String introduced;
	String discontinued;
	String compagnyId;
       
	
    @GetMapping("addComputer")
	public void addComputer() {
    	
    }
	
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public AddComputerController() {
//        super();
//    }
//    
////	@Override
////	public void init(ServletConfig config) throws ServletException {
////		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
////		super.init(config);
////	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		
//		List<CompanyDTO> companyDTOcollection = addComputerService.getListCompany();
//		request.setAttribute("listCompany", companyDTOcollection);
//
//		request.getRequestDispatcher("views/addComputer.jsp").forward(request,response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		name = request.getParameter("computerName");
//		introduced = request.getParameter("introduced");
//		discontinued = request.getParameter("discontinued");
//		compagnyId = request.getParameter("companyId");
//		addComputerService.addComputer(name, introduced, discontinued, compagnyId);
//		doGet(request, response);
//	}

}
