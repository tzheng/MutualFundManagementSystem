package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import databean.CustomerBean;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionDAO;

public class CustomerMainPanelAction extends Action {
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	
	public CustomerMainPanelAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}
	
	public String getName() { return "customer-mainpanel.do"; }
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			int customerId = (Integer) request.getSession(false).getAttribute("customerId");
			CustomerBean customer = customerDAO.read(customerId);
			
			//set current balance for customer.
			DecimalFormat format = new DecimalFormat("#,##0.00");
			request.setAttribute("currentBalance", format.format(customer.getCash()));
			// get the last trading date of this customer
			Date lastTradingDate = transactionDAO.getCustomerLastTradeDate(customerId);
			SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/yyyy");
			request.setAttribute("lastTradingDate", sdf.format(lastTradingDate));
			//get customer's Rejected transactions of his/her Last Trading Day
			request.setAttribute("processedNumber", transactionDAO.getCustomerTransactionNum(customerId, 1, lastTradingDate));
			//get customer's Processed transactions of his/her Last Trading Day
			request.setAttribute("rejectedNumber", transactionDAO.getCustomerTransactionNum(customerId, -1, lastTradingDate));

			return "customer-mainpanel.jsp";
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
