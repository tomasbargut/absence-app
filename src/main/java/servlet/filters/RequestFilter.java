package servlet.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataRequest;
import entities.Provider;
import entities.Request;
import entities.User;

/**
 * RequestFilter
 */
@WebFilter(filterName="request")
public class RequestFilter implements Filter {
    private final DataRequest dataRequest;
    public RequestFilter(){
        dataRequest = new DataRequest();
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                HttpServletRequest req = (HttpServletRequest) request;
                HttpServletResponse res = (HttpServletResponse) response;
                HttpSession session = req.getSession();
                if(session.getAttribute("administrator") == null){
                    int requestID = Integer.parseInt(req.getRequestURI().split("/")[3]);
                    Request mirequest = dataRequest.get(requestID);
                    if(mirequest == null){
                        res.sendError(404);
                        return;
                    }
                    Provider provider = (Provider) session.getAttribute("provider");
                    User petioner = (User) session.getAttribute("user");
                    if((provider != null && provider.getUserID() != mirequest.getProvider().getUserID())
                        || (petioner != null && petioner.getUserID() != mirequest.getPetitioner().getUserID())){
                        res.sendError(403);
                        return;
                    }
                }
                chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        
    }
    
}