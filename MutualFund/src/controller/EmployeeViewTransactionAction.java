package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.TransactionHistoryBean;
import formbean.CustomerIdForm;

import model.Model;
import model.MyDAOException;
import model.TransactionHistoryDAO;

public class EmployeeViewTransactionAction extends Action {
	private FormBeanFactory<CustomerIdForm> formBeanFactory = FormBeanFactory.getInstance(CustomerIdForm.class);
	private TransactionHistoryDAO transactionHistoryDAO;
	
	public EmployeeViewTransactionAction(Model model) {
		transactionHistoryDAO = model.getTransactionHistoryDAO();
	}
	
	public String getName() { return "employeehistory.do"; }
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			TransactionHistoryBean[] historyList;
			if (request.getParameter("customerId") != null) {
				CustomerIdForm form = formBeanFactory.create(request);
				errors.addAll(form.getValidationErrors());
				if (errors.size() != 0) {
					return "employee-viewtransaction.jsp";
				}
				historyList = transactionHistoryDAO.getTransactions(form.getCustomerIdasInt());
			}
			else {
				historyList = transactionHistoryDAO.getTransactions();
			}
			
			request.setAttribute("transactionHistory", historyList);
			return "employee-viewtransaction.jsp";
			
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "employee-viewtransaction.jsp";
		}
		
/**
		try {
			TransactionHistoryBean[] historyList = transactionHistoryDAO.getTransactions();
			request.setAttribute("transactionHistory", historyList);
			return "employee-viewtransaction.jsp";
			
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
		**/


	}
	
}
