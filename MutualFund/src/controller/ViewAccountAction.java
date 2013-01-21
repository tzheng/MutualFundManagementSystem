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
import model.PositionDAO;
import model.TransactionDAO;
import databean.CustomerBean;
import databean.PositionBean;

public class ViewAccountAction extends Action {
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	
	public ViewAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() { return "view-account.do"; }

	
	public String perform(HttpServletRequest request) {
        // Set up the errors list
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			//int customerId = (Integer) request.getSession(false).getAttribute("customerId");
			//when test, there is no session, use CustomerBean customer = customerDAO.read(1);
			CustomerBean customer = customerDAO.read(1);
			request.setAttribute("customer", customer);
			
			//PositionBean[] positionList = positionDAO.getUserPositions(customerId);
			PositionBean[] positionList = positionDAO.getUserPositions(1);
			request.setAttribute("positionList", positionList);
			
			

	        return "customer-viewaccount.jsp";
        } catch (MyDAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
