package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import databean.TransactionHistoryBean;

import model.Model;
import model.MyDAOException;
import model.TransactionHistoryDAO;

public class EmployeeViewTransactionAction extends Action {
	
	private TransactionHistoryDAO transactionHistoryDAO;
	
	public EmployeeViewTransactionAction(Model model) {
		transactionHistoryDAO = model.getTransactionHistoryDAO();
	}
	
	public String getName() { return "employeehistory.do"; }
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			TransactionHistoryBean[] historyList = transactionHistoryDAO.getTransactions();
			request.setAttribute("transactionHistory", historyList);
			return "employee-viewtransaction.jsp";
			
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
	
}
