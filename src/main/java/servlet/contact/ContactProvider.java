package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.*;
import logic.contact.*;
import logic.exceptions.*;

//PLACEHOLDER CLASS

/**
 * Servlet implementation class Signup
 */
@WebServlet("/contact")
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerContact cc;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newContact() {
        super();
		this.cc = new cc();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/singup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User(request);
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/singup.jsp");
		try {
			controllerUser.save(user);
			session.setAttribute("user", user);
			response.sendRedirect("index.jsp");
		} catch (UserException e) {
			System.out.print(e.getMessage());
			session.setAttribute("error", e.getMessage());
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			dispatcher.forward(request, response);
		} catch (Exception e) {
			session.setAttribute("error", "Servicio no disponible");
			dispatcher.forward(request, response);
		} 
	}

}
