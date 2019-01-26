package servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.ControllerCRUDContact;
import entities.Chat;
import entities.Provider;
import entities.User;
import entities.Provision;
import entities.ProvisionRequest;
/**
 * Servlet implementation class Contact
 */
@WebServlet("/contactProvider")
public class ContactProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactProvider() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Contact/contactProvider.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		HttpSession session = request.getSession();
		ControllerCRUDContact controller = new ControllerCRUDContact();
		
		String error = null;
		try {
			if(session != null) {
				//Data recovery from Session and others
				User user = (User)session.getAttribute("user");
				Provision provision = (Provision)request.getAttribute("provision");
				Provider provider = (Provider)request.getAttribute("provider");
				LocalDateTime requestDate = LocalDateTime.now();
				
				//New chat instance and addition of first message
				int newchatID = user.getUserID()+provider.getUser_id()+Integer.parseInt((requestDate).toString());
				Chat chat = new Chat(newchatID);
				chat.addChatLine(message, requestDate, user.getUsername());
				
				//New request instance using all of above
				ProvisionRequest userRequest = new ProvisionRequest(user, provision, provider, requestDate, chat);
				
				//Contact controller call and userRequest update with retrieved DB-generated requestID
				userRequest = controller.saveContact(userRequest, chat); //Should i pass chat? or should i retrieve it inside the controller?
				session.setAttribute("request", userRequest);
			}else {
				error = "Debe ingresar con su cuenta para poder contactar";
				response.sendRedirect("signin");
			}
		} catch (Exception e) {
			error = e.getMessage();
		}
		if(error != null) {
			request.setAttribute("error", error); //por que? no deberia estar en el bloque catch?
			request.getRequestDispatcher("WEB-INF/Contact/contactSuccess").forward(request, response);//Donde seteo el response? hay que? cuando?
		}	
	}
}