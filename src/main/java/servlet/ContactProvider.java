package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("ACTION");

		String publicationID = Utils.getStringValue(request, "publicationID", null);
		String mensaje = Utils.getStringValue(request, "requestMessage", null);
		String fechaInicio = Utils.getStringValue(request, "fechaInicio", null);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Request solicitud = null;
		PrintWriter pw = response.getWriter();

		switch (action) {
			case "CANCELAR_CONTACTO":
				try {
					String resultado = cc.deleteRequest(user, publicationID);
				} catch (ContactException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
				}
				break;

            case "CARGAR_SOLICITUDES":
            try {

            } catch (Exception e) {
				
            }
            break;
		default:
			break;
		}
	}
}