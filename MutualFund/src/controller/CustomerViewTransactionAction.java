package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import databean.CustomerBean;
import databean.TransactionHistoryBean;


import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionHistoryDAO;

public class CustomerViewTransactionAction extends Action {
	
	private TransactionHistoryDAO transactionHistoryDAO;
	private CustomerDAO customerDAO;
	public CustomerViewTransactionAction(Model model) {
		transactionHistoryDAO = model.getTransactionHistoryDAO();
		customerDAO = model.getCustomerDAO();
	}
	
	public String getName() { return "customerhistory.do"; }
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			int customerId = (Integer) request.getSession(false).getAttribute("customerId");
			
			CustomerBean customer = customerDAO.read(customerId);
			TransactionHistoryBean[] historyList = transactionHistoryDAO.getTransactions(customer.getCustomerId());
			request.setAttribute("transactionHistory", historyList);
			return "customer-viewtransaction.jsp";
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} 
	}
}
