package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataCategory;
import data.DataService;
import entities.Category;
import entities.Provider;
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
    private final DataCategory dataCategory;
    private final DataService dataService;

    public ServiceServlet() {
        super();
        controllerService = new ControllerService();
        dataCategory = new DataCategory();
        dataService = new DataService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /***
         * Si no se le pasa el id por querystring , Muestra el un form para crear un
         * nuevo servicio solo para un proveedor
         * 
         * Si se le pasa el id En case de que sea proveeder o admin lo edita
         */
        HttpSession session = request.getSession();
        String qString = request.getQueryString();
        Integer serviceID;
        Provider provider = (Provider) session.getAttribute("provider");
        RequestDispatcher dispatcher;
        ArrayList<Category> categories = dataCategory.getAll();
        session.setAttribute("categories", categories);
        if (qString == null) {
            session.setAttribute("service", null);
            if (provider != null) {
                dispatcher = request.getRequestDispatcher("WEB-INF/ServiceForm.jsp");
                dispatcher.forward(request, response);
                return;
            } else {
                response.sendError(403);
            }
        } else {
            try {
                serviceID = Integer.parseInt(qString);
            } catch (Exception e) {
                response.sendError(400);
                return;
            }
            Service service;
            try {
                service = dataService.get(serviceID);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                response.sendError(500);
                return;
            }
            if(service == null){
                response.sendError(404);
                return;
            }
            session.setAttribute("service", service);
            if(provider != null){
                dispatcher = request.getRequestDispatcher("WEB-INF/ServiceForm.jsp");    
            }else if(session.getAttribute("administrator") != null){
                dispatcher = request.getRequestDispatcher("WEB-INF/AdminServiceForm.jsp");
            }else{
                dispatcher = request.getRequestDispatcher("WEB-INF/Service.jsp");
            }
            dispatcher.forward(request, response);
            return;
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Service service;
        Provider provider = (Provider) session.getAttribute("provider");
        String qString = request.getQueryString();
        String[] categoriesIDsString = request.getParameterValues("categories");
        ArrayList<Category> categoriesTodas = (ArrayList<Category>) session.getAttribute("categories");
        ArrayList<Category> categories = new ArrayList<Category>();
        if(categoriesIDsString != null){
            for(String categoryIDString: categoriesIDsString){
                int categoryID;
                try{
                    categoryID = Integer.parseInt(categoryIDString);
                }catch(Exception e){
                    response.sendError(400);
                    return;
                }
                for(Category category: categoriesTodas){
                    if(category.getCategoryID() == categoryID){
                        categories.add(category);
                        break;
                    }
                }
            }
        }
        if(qString == null){
            service = new Service(request);
            service.setProvider(provider);
            service.setCategories(categories);
            try {
                controllerService.save(service);
            } catch (ServiceException e) {
                session.setAttribute("error", e.getMessage());
                response.sendRedirect(request.getContextPath()+"/service");
                return;
            }catch( SQLException e){
                response.sendError(500);
                return;
            }
            response.sendRedirect(request.getContextPath() + "/me");
            return;
        }else{
            Integer serviceID;
            try{
                serviceID = Integer.parseInt(qString);
            }catch(Exception e){
                response.sendError(400);
                return;
            }
            service = (Service) session.getAttribute("service");
            // service.setServiceID(serviceID);
            service.setTitle(request.getParameter("title"));
            service.setDesc(request.getParameter("desc"));
            service.setCategories(categories);
            try{
                controllerService.update(service);
            }catch(ServiceException e){
                session.setAttribute("error", e.getMessage());
                response.sendRedirect(request.getContextPath()+"/service?"+qString);
                return;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                response.sendError(500);
                return;
            }
            response.sendRedirect(request.getContextPath()+"/me");
            return;
        }
    }
}