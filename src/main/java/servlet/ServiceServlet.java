package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataPublication;
import entities.Provider;
import entities.Publication;
import entities.Service;
import logic.ControllerService;
import logic.exceptions.ServiceException;

/**
 * Service
 */
@WebServlet("/service")
public class ServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ControllerService controllerService;
    private final DataPublication dataPublication;

    public ServiceServlet() {
        super();
        controllerService = new ControllerService();
        dataPublication = new DataPublication();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ServiceForm.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Service service = new Service(request);
        HttpSession session = request.getSession();
        try {
            if (controllerService.save(service)) {
                session.setAttribute("success", "ok"); // TODO: Poner mensaje de exito
                Provider provider = (Provider) session.getAttribute("provider");
                dataPublication.save(new Publication(service, provider));
                response.sendRedirect(request.getContextPath() + "/me");
            } else {
                response.sendError(500);
            }
        } catch (ServiceException e) {
            session.setAttribute("error", e.getMessage());
            doGet(request, response);
        }
    }

}