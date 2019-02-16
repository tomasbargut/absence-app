package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataCategory;
import data.DataPublication;
import entities.Category;
import entities.Provider;
import entities.Publication;
import entities.Service;
import logic.ControllerService;
import logic.exceptions.ServiceException;

/**
 * Service
 */
@WebServlet("/service")
public class ServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ControllerService controllerService;
    private final DataPublication dataPublication;
    private final DataCategory dataCategory;

    public ServiceServlet() {
        super();
        controllerService = new ControllerService();
        dataPublication = new DataPublication();
        dataCategory = new DataCategory();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ServiceForm.jsp");
        ArrayList<Category> categories = dataCategory.getAll();
        HttpSession session = request.getSession();
        session.setAttribute("categories", categories);
        dispatcher.forward(request, response);
    }
    // FIXME: LOGICA REPETIDA EN SERVICEEDIT
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Service service = new Service(request);
        String[] categoriesIDsString = request.getParameterValues("cateogories");
        ArrayList<Category> categories = (ArrayList<Category>) session.getAttribute("categories");
        ArrayList<Category> categoriesService = new ArrayList<Category>();
        for(String categoryIDString: categoriesIDsString){
            int categoryID = Integer.parseInt(categoryIDString);
            for(Category category: categories){
                if(category.getCategoryID() == categoryID){
                    categoriesService.add(category);
                    break;
                }
            }
        }
        service.setCategories(categoriesService);
        try {
            if (controllerService.save(service)) {
                session.setAttribute("success", "ok"); // TODO: Poner mensaje de exito
                Provider provider = (Provider) session.getAttribute("provider");
                dataPublication.save(new Publication(service, provider));
                response.sendRedirect(request.getContextPath() + "/me");
            } else {
                response.sendError(500);
            }
        } catch (ServiceException e) {
            session.setAttribute("error", e.getMessage());
            doGet(request, response);
        }
    }

}