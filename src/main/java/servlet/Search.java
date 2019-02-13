package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// TODO: Agregar el map correcto
@WebServlet("/servlet")
// TODO: Poner nombre de clase correcto 

public class Search extends HttpServlet {
    private static final long serialVersionUID = -2099634213603977903L;

    public Search() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

    }
}