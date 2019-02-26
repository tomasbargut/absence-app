package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataCategory;
import data.DataService;
import entities.Category;
import entities.Service;

/**
 * Search
 */
@WebServlet("/search")
public class Search extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private final DataCategory dataCategory;
    private final DataService dataService;

    public Search(){
        dataCategory = new DataCategory();
        dataService = new DataService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String busqueda = req.getParameter("q");
        try{
            String[] categoriesIDsStrings = req.getParameterValues("categorias");
            ArrayList<Service> services = new ArrayList<Service>();
            if(categoriesIDsStrings != null || busqueda != null){
                if(categoriesIDsStrings != null){
                    ArrayList<Integer> categorias = new ArrayList<Integer>();
                    for(String s: categoriesIDsStrings){
                        categorias.add(Integer.parseInt(s));
                    }
                    services = dataService.getByCategories(categorias);
                }else{
                    services = dataService.all();
                }
                ArrayList<Service> serviciosFiltrados = new ArrayList<Service>();
                if(busqueda!= null){
                    for(Service service: services){
                        if(service.getTitle().contains(busqueda)){
                            serviciosFiltrados.add(service);
                        }
                    }
                }else{
                    serviciosFiltrados = services;
                }
                session.setAttribute("services", serviciosFiltrados);
            }
        }catch(SQLException exception){
            session.setAttribute("error", exception.getMessage());
        }catch(Exception e){
            session.setAttribute("error", "Categorias invalidas");
        }    
        resp.sendRedirect(req.getContextPath());
    }
}