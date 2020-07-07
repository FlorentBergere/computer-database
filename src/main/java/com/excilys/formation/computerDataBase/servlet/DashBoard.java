package com.excilys.formation.computerDataBase.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.computerDataBase.mapper.ComputerMapper;
import com.excilys.formation.computerDataBase.model.Computer;
import com.excilys.formation.computerDataBase.model.DTO.ComputerDTO;
import com.excilys.formation.computerDataBase.service.DashBoardService;

/**
 * Servlet implementation class DashBoard
 */

@WebServlet(urlPatterns = "/DashBoard")
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private DashBoardService dashBoardService;
	
	private int pageNumber = 0;
	private int nbEntryPerPage = 10;
	private int numberMaxPage;
	private List<Integer> listPage;
    private String search;
    private List<ComputerDTO> computerDTOCollection;
    private Computer.atributes atribute;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashBoard() {
        super();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if (request.getParameter("pageNumber") != null) {
			pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
		}
		
		if (request.getParameter("nbEntryPerPage") != null) {
			nbEntryPerPage = Integer.valueOf(request.getParameter("nbEntryPerPage"));
		}
		
		atribute = ComputerMapper.parseAtribute((request.getParameter("orderBy")));
		
		search = request.getParameter("search");
		if(atribute == null) {
			if (search != null && !search.isEmpty()) {
				computerDTOCollection = dashBoardService.findAllByPage(nbEntryPerPage,pageNumber,search);
			}else {
				computerDTOCollection = dashBoardService.findAllByPage(nbEntryPerPage,pageNumber);
			}
		}else{
			if (search != null && !search.isEmpty()) {
				computerDTOCollection = dashBoardService.findAllByPage(nbEntryPerPage,pageNumber,search,atribute);
			}else {
				computerDTOCollection = dashBoardService.findAllByPage(nbEntryPerPage,pageNumber,atribute);
			}
		}
		
		

		numberMaxPage = dashBoardService.getNumberMaxPage();
		listPage = dashBoardService.getListPage();
		request.setAttribute("computerDTOCollection", computerDTOCollection);
		request.setAttribute("nbComputer", dashBoardService.countComputer());
		request.setAttribute("nbPage", numberMaxPage);
		request.setAttribute("listPage", listPage);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("nbEntryPerPage", nbEntryPerPage);
		

		
		request.getRequestDispatcher("views/dashboard.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String idsToDelete = null;
		idsToDelete = request.getParameter("selection");
		if (idsToDelete != null && !idsToDelete.isEmpty() ) {
			dashBoardService.delete(idsToDelete);
		}
		
		
		search = request.getParameter("search");
		doGet(request, response);
	}

}
