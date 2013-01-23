package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.TransactionHistoryBean;

import formbean.CustomerIdForm;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionHistoryDAO;

public class CustomerViewTransactionAction extends Action {
	private FormBeanFactory<CustomerIdForm> formBeanFactory = FormBeanFactory.getInstance(CustomerIdForm.class);
	
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
			/**
			CustomerIdForm form = formBeanFactory.create(request);
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "error.jsp";
			}
			**/
			int customerId = (Integer) request.getSession(false).getAttribute("customerId");
			System.out.println(customerId);
			
			CustomerBean customer = customerDAO.read(customerId);
			//TransactionHistoryBean[] historyList = transactionHistoryDAO.getTransactions(form.getCustomerIdasInt()); 
			TransactionHistoryBean[] historyList = transactionHistoryDAO.getTransactions(customer.getCustomerId());
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
