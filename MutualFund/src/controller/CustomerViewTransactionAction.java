package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.TransactionHistoryDAO;

public class CustomerViewTransactionAction extends Action {
	
	private TransactionHistoryDAO transactionHistoryDAO;
	
	public CustomerViewTransactionAction(Model model) {
		transactionHistoryDAO = model.getTransactionHistoryDAO();
	}
	
	public String getName() { return "customerhistory.do"; }
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
	}
}
