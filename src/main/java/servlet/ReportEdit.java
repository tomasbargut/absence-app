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
import logic.ControllerReport;

/**
 * ReportEdit
 */
@WebServlet("/report/*")
public class ReportEdit extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private final ControllerReport controllerReport;
    public ReportEdit(){
        super();
        controllerReport = new ControllerReport();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/reportAdmin.jsp");
        dispatcher.forward(req, resp);
    }
    //EL report viene del filter
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Report report = (Report) session.getAttribute("report");
        report.setStatus(req.getParameter("status"));
        if(controllerReport.update(report)){
            resp.sendRedirect(req.getContextPath() + "/me");
        }else{
            resp.sendError(500);
        }
    }
    
}