package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataRequest;
import entities.Request;
import entities.Review;
import logic.ControllerReview;
import logic.exceptions.ReviewException;

/**
 * ReviewServlet
 */
@WebServlet("/review")
public class ReviewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ControllerReview controllerReview;

    public ReviewServlet() {
        super();
        controllerReview = new ControllerReview();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/reviewForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Request request = (Request) session.getAttribute("peticion");
        Integer pointsGiven = null;
        try{
            pointsGiven = Integer.parseInt(req.getParameter("pointsGiven"));
        }catch(Exception e){
            resp.sendError(400);
            return;
        }
        Review review = new Review(req);
        review.setPointsGive(pointsGiven);
        review.setRequest(request);
        try {
            controllerReview.save(review);
        } catch (SQLException e) {
            resp.sendError(500);
            return;
        } catch (ReviewException e) {
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect(req.getContextPath()+"/review");
        }
        resp.sendRedirect(req.getContextPath()+"/request?"+ review.getRequest().getRequestID());
    }
}