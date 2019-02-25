package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataProvider;
import entities.Provider;
import entities.User;
import logic.ControllerContact;

/**
 * Servlet implementation class UserMe
 * 
 * @param <Request>
 */
@WebServlet("/me")
public class UserMe<Request> extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private DataProvider dataProvider;
	private ControllerContact cc;

	public UserMe() {
		super();
		dataProvider = new DataProvider();
		cc = new ControllerContact();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("administrator") != null) {
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			List<Request> solicitudes = (List<Request>) cc.getAllByProvider(user);
			session.setAttribute("success", "ok");
			session.setAttribute("solicitudes", solicitudes);

			request.getRequestDispatcher("/WEB-INF/me.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
