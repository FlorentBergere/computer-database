package com.excilys.formation.computerDataBase.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.service.DashBoardService;

/**
 * Servlet implementation class DashBoard
 */

@WebServlet(urlPatterns = "/DashBoard")
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DashBoardService dashBoardService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashBoard() {
        super();
        this.dashBoardService = new DashBoardService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		List<ComputerDTO> computerDTOCollection = dashBoardService.findAll();
		request.setAttribute("computerDTOCollection", computerDTOCollection);
		request.setAttribute("nbComputer", dashBoardService.countComputer());
		
		request.getRequestDispatcher("views/dashboard.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
