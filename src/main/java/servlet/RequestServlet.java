package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataReport;
import data.DataRequest;
import data.DataReview;
import entities.Report;
import entities.Request;
import entities.Review;
import entities.Service;
import entities.User;
import logic.ControllerRequest;
import logic.exceptions.RequestException;

/**
 * RequestServlet
 */
@WebServlet("/request")
public class RequestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ControllerRequest controllerRequest;
    private final DataRequest dataRequest;
    private final DataReport dataReport;
    private final DataReview dataReview;

    public RequestServlet() {
        controllerRequest = new ControllerRequest();
        dataRequest = new DataRequest();
        dataReport = new DataReport();
        dataReview = new DataReview();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String qString = req.getQueryString();
        if(qString == null){
            resp.sendError(400);
            return;
        }
        Integer requestID;
        try{
            requestID = Integer.parseInt(qString);
        }catch(Exception e){
            resp.sendError(400);
            return;
        }
        Request request = null;
        Report report = null;
        Review review = null;
        try {
            request = dataRequest.get(requestID);
            if(request != null){
                report = dataReport.get(request.getRequestID());
                review = dataReview.get(request.getRequestID());
            }
        } catch (SQLException e) {
            resp.sendError(500);
            return;
        }
        if(request == null){
            resp.sendError(404);
            return;
        }
        User user = (User) session.getAttribute("user");
        if(!(request.getService().getProvider().getUserID() == user.getUserID() ||
            request.getPetitioner().getUserID() == user.getUserID() )){
                resp.sendError(403);
                return;
        }
    
        session.setAttribute("peticion", request);
        session.setAttribute("review", review);
        session.setAttribute("report", report);
        req.getRequestDispatcher("WEB-INF/Request.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Service service = (Service) session.getAttribute("service");
        User user = (User) session.getAttribute("user");
        String message = (String) req.getParameter("message");
        if (user == null) {
            session.setAttribute("error", "Necesitas estar logueado para solicitar este servicio");
            resp.sendRedirect(req.getContextPath() + "/service?" + service.getServiceID());
            return;
        }
        Request request = new Request(user, service, message);
        request.setMessage(message);
        try {
            controllerRequest.save(request);
        } catch (SQLException e) {
            resp.sendError(500);
        } catch (RequestException e) {
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/service?" + service.getServiceID());
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/me");
    }
}