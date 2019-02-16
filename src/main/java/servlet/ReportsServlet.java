package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Publication;
import entities.Report;
import logic.ControllerReport;
import logic.exceptions.ReportException;

/**
 * Reports
 */
// TODO: TESTEAR
@WebServlet("/report")
public class ReportsServlet extends HttpServlet{
    
    private static final long serialVersionUID = 1L;
    private final ControllerReport controllerReport;
    public ReportsServlet(){
        super();
        controllerReport = new ControllerReport();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: EVALUAR UN FILTER CON ESTO
        HttpSession session = req.getSession();
        if(session.getAttribute("publication") == null){
            resp.sendError(404);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/reportForm.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("publication") == null){
            resp.sendError(400);
        }
        Publication publication = (Publication) session.getAttribute("publication");
        Report report = new Report(req, publication);
        try{
            if(controllerReport.save(report)){
                resp.sendRedirect(req.getContextPath());
            }else{
                resp.sendError(500);
            }
        }catch(ReportException e){
            session.setAttribute("error", e.getMessage());
        }
    }
    
}