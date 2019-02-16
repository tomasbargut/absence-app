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
import entities.Category;
import entities.Service;
import logic.ControllerService;
import logic.exceptions.ServiceException;

/**
 * ServiceEdit
 */
// TODO: TESTEAR
@WebServlet("/service/*")
public class ServiceEdit extends HttpServlet {
    private final DataCategory dataCategory;
    private final ControllerService controllerService;

    public ServiceEdit() {
        super();
        dataCategory = new DataCategory();
        controllerService = new ControllerService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Service service = (Service) session.getAttribute("service");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ServiceForm.jsp");
        ArrayList<Category> categories = dataCategory.getAll();
        session.setAttribute("categories", categories);
        dispatcher.forward(request, response);
    }
    // FIXME: LOGICA REPETIDA EN SERVICESERVLET
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Service service = (Service) session.getAttribute("service");
        service.setDesc(request.getParameter("desc"));
        service.setTitle(request.getParameter("title"));
        String[] categoriesIDsString = request.getParameterValues("cateogories");
        ArrayList<Category> categories = (ArrayList<Category>) session.getAttribute("categories");
        ArrayList<Category> categoriesService = new ArrayList<Category>();
        //TODO: MEJORAR ESTO
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
            controllerService.update(service);
        }catch(ServiceException e){
            session.setAttribute("error", e.getMessage());
        }
        doGet(request,response);
    }
    
}