package servlet.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Provider;
import entities.Service;

/**
 * ServiceFilter
 */
@WebFilter({"/service/*"})
public class ServiceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                int serviceID = Integer.parseInt(request.getRequestURI().split("/")[3]);
                HttpSession session = request.getSession();
                Provider provider = (Provider) session.getAttribute("provider");
                Service service = null;
                // El usuario no puede editar un servicio que no es de el
                for(Service s: provider.getServices()){
                    if(s.getServiceID() == serviceID){
                        service = s;
                        break;
                    }
                }
                if(service == null){
                    response.sendError(403);
                    return;
                }
                session.setAttribute("service", service);
                chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }

    
}