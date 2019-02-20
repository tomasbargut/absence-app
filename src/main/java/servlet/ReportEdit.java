package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.Request;
import logic.ControllerContact;
import logic.ControllerReport;
import logic.exceptions.ContactException;

/**
 * ReportEdit
 */
@WebServlet("/report/*")
public class ReportEdit extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private final ControllerContact controllerContact;
    public ReportEdit(){
        super();
        controllerContact = new ControllerContact();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/reportAdmin.jsp");
        dispatcher.forward(req, resp);
    }
    //La request viene del filter
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Request request = (Request) session.getAttribute("request");
        request.getReport().setStatus(req.getParameter("status"));
        try{
            if(controllerContact.update(request)){
                resp.sendRedirect(req.getContextPath() + "/me");
            }else{
                resp.sendError(500);
            }
        }catch(ContactException e){
            session.setAttribute("error", e.getMessage());
            doGet(req, resp);
            return;
        }
    }
    
}