package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.protobuf.Service;

import data.DataProvider;
import data.DataService;
import entities.Provider;

/**
 * Servlet implementation class UserMe
 */
@WebServlet("/me")
public class UserMe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private final DataService dataService;

	public UserMe() {
		super();
		dataService = new DataService();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Provider provider = (Provider) session.getAttribute("provider");
		if (provider != null) {
			try {
				session.setAttribute("services", dataService.getByProvider(provider));
			} catch (SQLException e) {
				response.sendError(500);
				return;
			}
		}
		if(request.getSession().getAttribute("administrator") != null){
			response.sendRedirect(request.getContextPath()+ "/admin");
		}else{
			request.getRequestDispatcher("/WEB-INF/me.jsp").forward(request, response);
		}
	}
}
