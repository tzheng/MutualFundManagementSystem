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

public class CustomerViewTransactionAction extends Action {
	private FormBeanFactory<CustomerIdForm> formBeanFactory = FormBeanFactory.getInstance(CustomerIdForm.class);
	
	private TransactionHistoryDAO transactionHistoryDAO;
	
	public CustomerViewTransactionAction(Model model) {
		transactionHistoryDAO = model.getTransactionHistoryDAO();
	}
	
	public String getName() { return "customerhistory.do"; }
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			/**
			CustomerIdForm form = formBeanFactory.create(request);
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "error.jsp";
			}
			**/
			//TransactionHistoryBean[] historyList = transactionHistoryDAO.getCustomerTransaction(form.getCustomerIdasInt()); 
			TransactionHistoryBean[] historyList = transactionHistoryDAO.getCustomerTransaction(1);
			request.setAttribute("transactionHistory", historyList);
			return "customer-viewtransaction.jsp";
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} 
		/**
		catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}  
		**/
	}
}
