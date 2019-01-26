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
import logic.ControllerProvider;

/**
 * Servlet implementation class ProviderForm
 */
@WebServlet("/provider")
public class ProviderForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerProvider controller;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProviderForm() {
        super();
        controller = new ControllerProvider();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/providerform.jsp");
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!= null && session.getAttribute("provider") == null) {
			dispatcher.forward(request, response);
		}else {
			request.getSession().setAttribute("error", "Tenes que estar logueado wachim");
			response.sendRedirect("");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/providerform.jsp");
		try {
			Provider provider = controller.save_provider(new Provider(request));
			request.getSession().setAttribute("provider", provider);
			response.sendRedirect("me");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.getSession().setAttribute("error", e.getMessage());
			dispatcher.forward(request, response);
		}
	}

}
