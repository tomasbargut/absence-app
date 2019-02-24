package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Report;
import entities.Request;
import logic.ControllerRequest;
import logic.ControllerReport;
import logic.exceptions.RequestException;

/**
 * Reports
 */
// TODO: TESTEAR
@WebServlet("/report")
public class ReportsServlet extends HttpServlet{
    
    private static final long serialVersionUID = 1L;
    private final ControllerReport controllerReport;
    private final ControllerRequest controllerContact;
    public ReportsServlet(){
        super();
        controllerReport = new ControllerReport();
        controllerContact = new ControllerRequest();
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
            return;
        }
        Request request= (Request) session.getAttribute("request");
        Report report = new Report(req);
        request.setReport(report);
        try{
            controllerContact.update(request);
        }catch(RequestException e){
            session.setAttribute("error", e.getMessage());
        }catch(SQLException e){
            resp.sendError(500);
        }
        resp.sendRedirect(req.getContextPath());
    }
    
}