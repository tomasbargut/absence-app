package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataPublication;
import entities.Publication;
import entities.Request;
import entities.User;
import logic.ControllerContact;
import logic.exceptions.ContactException;
import utils.Utils;

/**
 * Search
 */
@WebServlet("/")
public class Search extends HttpServlet{
    
    private static final long serialVersionUID = 1L;
    private final DataPublication dataPublication;
    private final ControllerContact controllerContact;

    public Search(){
        dataPublication = new DataPublication();
        controllerContact = new ControllerContact();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Publication> publications = new ArrayList<Publication>();
        String qs = req.getQueryString();
        for(Publication publication: dataPublication.all()){
            if(publication.getService().getTitle().contains(qs)){
                publications.add(publication);
            }
        }
        HttpSession session = req.getSession();
        session.setAttribute("publications", publications);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("ACTION");
        String publicationID = Utils.getStringValue(req, "publicationID", null);
        int providerID = Integer.parseInt(publicationID.substring(publicationID.length()/2 -1));
        int serviceID = Integer.parseInt(publicationID.substring(publicationID.length()/2, publicationID.length() - 1));
        Publication publication = null;
		String mensaje = Utils.getStringValue(req, "requestMessage", null);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
		for (Publication pub : (ArrayList<Publication>) session.getAttribute("publications")) {
            if(pub.getService().getServiceID() == serviceID && pub.getProvider().getUserID() == providerID){
                publication = pub;
                break;
            }
        }
        PrintWriter pw = resp.getWriter();
        switch (action) {
			case "SOLICITAR_CONTACTO":
				try {
                    Request request = new Request(user, publication.getService(), publication.getProvider(), null, mensaje);
                    controllerContact.save(request);
                    pw.print("{\"status\":\"success\"}");
                    return;
				} catch (ContactException e) {
                    pw.print("{\"status\":\"error\"}");
                    return;
				} 
        }
    }
}