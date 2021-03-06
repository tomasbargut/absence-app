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

import entities.User;
import logic.ControllerUser;
import logic.exceptions.UserException;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/singup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerUser controllerUser;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
		this.controllerUser = new ControllerUser();
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
			if(controllerUser.save(user)){
				session.setAttribute("user", user);
				response.sendRedirect("index.jsp");
			}else{
				response.sendError(500);
			}
		} catch (UserException e) {
			System.out.print(e.getMessage());
			session.setAttribute("error", e.getMessage());
			doGet(request, response);
		}
	}

}
