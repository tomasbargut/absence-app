package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataProvider;
import data.DataUser;
import entities.Provider;
import entities.User;
/**
 * Servlet implementation class Login
 */
@WebServlet("/singin")
public class Singin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataUser data_user ;
    private DataProvider data_provider;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Singin() {
        super();
        this.data_user = new DataUser();
        this.data_provider = new DataProvider();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/singin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("username");
		String error = null;
		HttpSession session = request.getSession();
		try {
			User user = data_user.getUserByUsername(nombre);
			if(user != null) {
				if(user.getPassword().equals(request.getParameter("password"))) {
					session.setAttribute("user", user);
					Provider provider = data_provider.get_provider_by_id(user.getUserID());
					if(provider != null) {
						session.setAttribute("provider", provider);
					}
					response.sendRedirect("/Absence");
				}else {
					error = "Grrr...";
				}
			}else {
				error = "Usuario o nombre incorrecto";
			}
		} catch (Exception e) {
			error = e.getMessage();
		}
		if(error != null) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("WEB-INF/singin.jsp").forward(request, response);
		}
	}
}