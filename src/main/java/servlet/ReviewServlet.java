package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataRequest;
import entities.Request;
import entities.Review;

/**
 * ReviewServlet
 */
@WebServlet("/review")
public class ReviewServlet extends HttpServlet{

    private final DataRequest dataRequest;
    public ReviewServlet(){
        super();
        dataRequest = new DataRequest();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/reviewForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Request request = (Request) session.getAttribute("request");
        Review review = new Review(req);
        request.setReview(review);
        if(dataRequest.update(request)){
            resp.sendRedirect(req.getContextPath());
        }else{
            resp.sendError(500);
        }
    }
}