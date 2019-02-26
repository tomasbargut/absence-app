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

import entities.Provider;
import entities.User;
import logic.ControllerProvider;
import logic.exceptions.ProviderException;

/**
 * Servlet implementation class ProviderForm
 */
@WebServlet("/provider")
public class ProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerProvider controllerProvider;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProviderServlet() {
		super();
		controllerProvider = new ControllerProvider();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		if (session.getAttribute("provider") != null) {
			response.sendError(403);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/providerform.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Provider provider = new Provider(request, (User) session.getAttribute("user"));
		try {
			controllerProvider.save(provider);
			session.setAttribute("provider", provider);
		} catch (ProviderException e) {
			session.setAttribute("error", e.getMessage()); // TODO: IMPLEMENTAR ENUM
		} catch (SQLException e) {
			response.sendError(500);
		}
		response.sendRedirect(request.getContextPath() + "/me");
	}

}
