package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Category;
import logic.ControllerCategory;
import logic.exceptions.CategoryException;

/**
 * AdminService
 */
@WebServlet(
    urlPatterns = {"/admin/category"},
    name = "admincategory"
)
public class AdminCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ControllerCategory controllerCategory;

    public AdminCategory(){
        super();
        controllerCategory = new ControllerCategory();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/CategoryForm.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Category category = new Category(request);
        HttpSession session = request.getSession();
        try {
            if(controllerCategory.save(category)){
                session.setAttribute("success", "ok");
                response.sendRedirect(request.getContextPath()+"/admin/category");
            }else{
                response.sendError(500);
            }
        } catch (CategoryException e) {
            session.setAttribute("error", e.getMessage());
            doGet(request, response);
        }
    }
}