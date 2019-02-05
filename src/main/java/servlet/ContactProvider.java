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
import logic.ControllerContact;
import logic.exceptions.ContactException;
import utils.*;

//PLACEHOLDER CLASS

/**
 * Servlet implementation class Contact/Request
 */
@WebServlet("/contact")
public class ContactProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerContact cc;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactProvider() {
		super();
		this.cc = new ControllerContact();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/contact/contactProviderModal.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("ACTION");

		String mensaje = Utils.getStringValue(request, "requestMessage", null);
		String fechaInicio = Utils.getStringValue(request, "fechaInicio", null);
		Integer publicationID = Utils.getIntValue(request, "publicationID", null);

		HttpSession session = request.getSession();
		User solicitante = (User) request.getAttribute("user");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");// contact/contactProviderModal

		switch (action) {

		case "SOLICITAR_CONTACTO":
			try {
				cc.newRequest(solicitante, publicationID, mensaje, fechaInicio);
				// TODO Respuesta a panel modal con datos de contacto logrado o error

			} catch (ContactException e) {
				e.printStackTrace();
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
			break;
		case "CANCELAR_CONTACTO":
			try {
				cc.deleteRequest(solicitante, publicationID, mensaje, fechaInicio);
				// COSAS QUE SE HACEN SI CANCELA CONTACTO
			} catch (ContactException e) {
				e.printStackTrace();
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
			break;
		default:
			break;
		}
	}
}