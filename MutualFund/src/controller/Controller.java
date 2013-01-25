package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	public void init() throws ServletException {
        Model model = new Model(getServletConfig());

        //Action.add(new AddAction(model));
        Action.add(new CustomerViewTransactionAction(model));
        Action.add(new EmployeeViewTransactionAction(model));
        Action.add(new CustomerLoginAction(model));
        Action.add(new EmployeeLoginAction(model));
        Action.add(new CreateAccountAction(model));
        Action.add(new ViewAccountAction(model));
        Action.add(new CreateFundAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new CreateEmployeeAccountAction(model));
        Action.add(new CustomerChangePwdAction(model));
        Action.add(new EmployeeChangePwdAction(model));
        Action.add(new EmployeeResetCustomerPwdAction(model));
        Action.add(new CustomerBuyFundAction(model));
        Action.add(new DepositCheckAction(model));
        Action.add(new RequestCheckAction(model));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        
        sendToNextPage(nextPage,request,response);
    }
    
    /*
     * Extracts the requested action and (depending on whether the user is logged in)
     * perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
        String      action = getActionName(servletPath);
        
        if (action.equals("start")) {
        	return "index.jsp";
        }
        
      	// Let the logged in user run his chosen action
		return Action.perform(action, request);
    }
    
    /*
     * If nextPage is null, send back 404
     * If nextPage ends with ".do", redirect to this page.
     * If nextPage ends with ".jsp", dispatch (forward) to the page (the view)
     *    This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
    	}
    	
    	if (nextPage.endsWith(".jsp")) {
	   		RequestDispatcher d = request.getRequestDispatcher(nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	throw new ServletException(Controller.class.getName()+".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}
