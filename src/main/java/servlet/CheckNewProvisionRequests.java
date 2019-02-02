package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Provider;
import entities.Request;

/**
 * Servlet implementation class Contact
 */
@WebServlet("/Contact/CheckNewProvisionRequests")
/**
 * @author ferna
 *
 */
public class CheckNewProvisionRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckNewProvisionRequests() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Contact/contactProvider.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// HttpSession session = request.getSession();
		// ControllerCRUDContact controller = new ControllerCRUDContact();

		// // TODO solo puede haber un unico par doGet/doPost por servlet? o el nombre del
		// // metodo no es determinante y puede tenerse varios con distinto nombre? Por
		// // ejemplo: doPostVerSolicitud y doPostResponderSolicitud

		// String error = null;
		// try {
		// 	if (session != null) {
		// 		Provider provider = (Provider) request.getAttribute("provider");
		// 		ArrayList<Request> provisionRequestList = new ArrayList<Request>();
		// 		provisionRequestList = controller.getAllContacts(provider);
		// 		request.setAttribute("provisionRequestList", provisionRequestList);
		// 		request.getRequestDispatcher("WEB-INF/Contact/checkNewProvisionRequests").forward(request, response);

		// 	} else {
		// 		error = "Debe ingresar con su cuenta para poder ver si tiene nuevas solicitudes de contacto";
		// 		response.sendRedirect("signin");
		// 	}
		// } catch (Exception e) {
		// 	error = e.getMessage();
		// }
		// if (error != null) {
		// 	request.setAttribute("error", error);
		// 	request.getRequestDispatcher("WEB-INF/Contact/contactSuccess").forward(request, response);
		// }
	}
}
