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

import logic.ControllerCRUDUser;
import logic.exceptions.UserException;
import entities.User;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/singup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
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
		ControllerCRUDUser controller = new ControllerCRUDUser();
		User user = new User();
		user.setUserID(1);
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/singup.jsp");
		try {
			user = controller.saveUser(user);
			session.setAttribute("user", user);
			response.sendRedirect("");
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
