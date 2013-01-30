package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basic.JSPNames;

public class SessionCheck implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        
        String      servletPath = request.getServletPath();
        String      action = getActionName(servletPath);
        
        //System.out.println(servletPath);
        
        Integer customerId = (Integer) request.getSession().getAttribute("customerId");
        String employeeUserName = (String) request.getSession().getAttribute("employeeUserName");
        
        if(customerId == null || employeeUserName == null)
        {
            response.sendRedirect("start");
            return;
        }
        
        if(customerId != null){ // it is a customer
        	if(!containsJSP(action, JSPNames.customerJSPs)){
        		response.sendRedirect("start");
        	}
        }
        
        if (employeeUserName != null){
        	if(!containsJSP(action, JSPNames.employeeJSPs)){
        		response.sendRedirect("start");
        	}
        }
        
        arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
	
	private boolean containsJSP(String action, String[] list){
    	for (String item : list	){
    		if(item.equals(action)){
    			return true;
    		}
    	}
    	
    	return false;
    }

}
