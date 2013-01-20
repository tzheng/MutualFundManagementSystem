/**
 * @author Team Snipers (Team 1)
 * Jan 19, 2013
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.MyDAOException;
import model.CustomerDAO;
import databean.CustomerBean;

public class ViewAccountAction extends Action {
	
	private CustomerDAO customerDAO;

	public ViewAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "view-account.do"; }

	
	public String perform(HttpServletRequest request) {
        // Set up the errors list
       
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			int customerId = (Integer) request.getSession(false).getAttribute("customerId");
			//when test, there is no session, use CustomerBean customer = customerDAO.read(1);
			CustomerBean customer = customerDAO.read(1);

			
			request.setAttribute("customer", customer);


	        return "customer-viewaccount.jsp";
        } catch (MyDAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
