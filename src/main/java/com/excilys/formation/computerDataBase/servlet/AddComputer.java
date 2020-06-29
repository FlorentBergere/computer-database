package com.excilys.formation.computerDataBase.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerDataBase.model.DTO.CompanyDTO;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.service.AddComputerService;
import com.excilys.formation.computerDataBase.service.ComputerService;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet(urlPatterns = "/addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddComputerService addComputerService;
	String name;
	String introduced;
	String discontinued;
	String compagnyId;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
        this.addComputerService = new AddComputerService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		List<CompanyDTO> companyDTOcollection = addComputerService.getListCompany();
		request.setAttribute("listCompany", companyDTOcollection);

		request.getRequestDispatcher("views/addComputer.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		name = request.getParameter("computerName");
		introduced = request.getParameter("introduced");
		discontinued = request.getParameter("discontinued");
		compagnyId = request.getParameter("companyId");
		addComputerService.addComputer(name, introduced, discontinued, compagnyId);
		doGet(request, response);
	}

}
