package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.*;
import logic.ControllerNotifications;
import utils.*;

/**
 * Servlet implementation class Contact/Request
 */
@WebServlet("/notifications")
public class Notifications extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ControllerNotifications cn;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notifications() {
        super();
        this.cn = new ControllerNotifications();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("ACTION");
        /*
         * String publicationID = Utils.getStringValue(request, "publicationID", null);
         * String mensaje = Utils.getStringValue(request, "requestMessage", null);
         * String fechaInicio = Utils.getStringValue(request, "fechaInicio", null);
         */
        HttpSession session = request.getSession();
        User proveedor = (User) session.getAttribute("user");
        Request notificaciones = null;

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");// contact/contactProviderModal
        PrintWriter pw = response.getWriter();

        switch (action) {

        case "CARGAR_NOTIFICACIONES":
            try {

                if (proveedor != null) {
                    notificaciones = cn.getNotifications(proveedor);
                    String jsonNotificaciones = notificaciones.toJsonSimplified();
                    System.out.println(jsonNotificaciones);

                    request.setAttribute("notificaciones", jsonNotificaciones);
                    pw.println(jsonNotificaciones);
                } else {
                    // ver como manejar esto, el login filter debe ser una mejor solucion
                    session.setAttribute("error", "Debes ingresar para continuar");
                    // antes de redirigir guardar locacion para regresar.
                    response.sendRedirect("/login"); // no puede redirigir.
                    dispatcher.forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                session.setAttribute("error", e.getMessage());
                dispatcher.forward(request, response);
            } catch (Exception e) {
                session.setAttribute("error", "Servicio no disponible");
                dispatcher.forward(request, response);
            }
            break;

            case "VER_TODAS":
            try {

                if (proveedor != null) {
                    //redireccion?
                } else {
                    // ver como manejar esto, el login filter debe ser una mejor solucion
                    session.setAttribute("error", "Debes ingresar para continuar");
                    // antes de redirigir guardar locacion para regresar.
                    response.sendRedirect("/login"); // no puede redirigir.
                    dispatcher.forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                session.setAttribute("error", e.getMessage());
                dispatcher.forward(request, response);
            } catch (Exception e) {
                session.setAttribute("error", "Servicio no disponible");
                dispatcher.forward(request, response);
            }
            break;

        default:
            break;
        }
    }
}