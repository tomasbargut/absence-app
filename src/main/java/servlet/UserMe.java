package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataProvider;
import entities.Provider;

/**
 * Servlet implementation class UserMe
 */
@WebServlet("/me")
public class UserMe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private DataProvider dataProvider;
    public UserMe() {
		super();
		dataProvider = new DataProvider();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null) {
			Provider provider = (Provider)session.getAttribute("provider");
			if(provider != null){
				session.setAttribute("provider", dataProvider.get(provider.getUserID()));
			}
			request.getRequestDispatcher("/WEB-INF/me.jsp").forward(request, response);
		}else {
			response.sendRedirect("singin");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
