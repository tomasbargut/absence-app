package servlet;

import java.io.IOException;

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

/**
 * Servlet implementation class ProviderForm
 */
@WebServlet("/provider")
public class ProviderForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerProvider controllerProvider;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProviderForm() {
        super();
        controllerProvider = new ControllerProvider();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		if(session.getAttribute("provider") != null){
			response.sendRedirect("me");
		}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/providerform.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null){
			response.sendError(403);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/providerform.jsp");
		try {
			Provider provider = new Provider(request, (User)session.getAttribute("user"));
			controllerProvider.save(provider);
			request.getSession().setAttribute("provider", provider);
			response.sendRedirect("me");
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("error", e.getMessage());
			dispatcher.forward(request, response);
		}
	}

}
