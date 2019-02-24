package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Request;
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

    public RequestServlet() {
        controllerRequest = new ControllerRequest();
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