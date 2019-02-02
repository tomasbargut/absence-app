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
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataUser dataUser ;
    private DataProvider dataProvider;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        this.dataUser = new DataUser();
        this.dataProvider = new DataProvider();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("username");
		String error = null;
		HttpSession session = request.getSession();
		try {
			User user = dataUser.getByUsername(nombre);
			if(user != null) {
				if(user.getPassword().equals(request.getParameter("password"))) {
					session.setAttribute("user", user);
					Provider provider = dataProvider.get(user.getUserID());
					if(provider != null) {
						session.setAttribute("provider", provider);
					}
					response.sendRedirect("");
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
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}
	}
}