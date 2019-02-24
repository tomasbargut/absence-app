package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Category;
import logic.ControllerCategory;

/**
 * Admin
 */
@WebServlet("/admin")
public class Admin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ControllerCategory controllerCategory;

    public Admin() {
        super();
        controllerCategory = new ControllerCategory();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/admin.jsp");

        HttpSession session = request.getSession();

        List<Category> categories = controllerCategory.getAll();
        session.setAttribute("success", "ok");
        session.setAttribute("categories", categories);

        dispatcher.forward(request, response);
    }
}