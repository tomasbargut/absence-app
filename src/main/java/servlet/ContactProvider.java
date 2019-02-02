package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Request;
import entities.User;
import logic.ControllerContact;
/**
 * Servlet implementation class Contact
 */
@WebServlet("/contactProvider")
public class ContactProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerContact controllerContact;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactProvider() {
        super();
        controllerContact = new ControllerContact();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/Contact/contactProvider.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		HttpSession session = request.getSession();		
		String error = null;
		try {
			if(session != null) {
				User user = (User)session.getAttribute("user");				
				Request userRequest = new Request(request);
				controllerContact.save(userRequest);
			}else {
				error = "Debe ingresar con su cuenta para poder contactar";
				response.sendRedirect("login");
			}
		} catch (Exception e) {
			error = e.getMessage();
		}
		if(error != null) {
			request.setAttribute("error", error); 
			request.getRequestDispatcher("WEB-INF/Contact/contactSuccess").forward(request, response);//Donde seteo el response? hay que? cuando?
		}	
	}
}