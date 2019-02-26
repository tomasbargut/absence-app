package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataCategory;

/**
 * index
 */
@WebServlet("/")
public class Index extends HttpServlet{

    private final DataCategory dataCategory;

    public Index(){
        dataCategory = new DataCategory();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("categories", dataCategory.getAll());
        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }
}