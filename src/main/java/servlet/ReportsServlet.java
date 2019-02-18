package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Report;
import entities.Request;
import logic.ControllerContact;
import logic.ControllerReport;
import logic.exceptions.ContactException;

/**
 * Reports
 */
// TODO: TESTEAR
@WebServlet("/report")
public class ReportsServlet extends HttpServlet{
    
    private static final long serialVersionUID = 1L;
    private final ControllerReport controllerReport;
    private final ControllerContact controllerContact;
    public ReportsServlet(){
        super();
        controllerReport = new ControllerReport();
        controllerContact = new ControllerContact();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/reportForm.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("request") == null){
            resp.sendError(400);
        }
        Request request= (Request) session.getAttribute("request");
        Report report = new Report(req);
        request.setReport(report);
        try{
            if(controllerContact.update(request)){
                resp.sendRedirect(req.getContextPath());
            }else{
                resp.sendError(500);
            }
        }catch(ContactException e){
            session.setAttribute("error", e.getMessage());
        }
    }
    
}