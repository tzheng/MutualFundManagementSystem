package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.PositionBean;
import formbean.LoginForm;

public class CustomerLoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	
	public CustomerLoginAction(Model model){
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		positionDAO = model.getPositionDAO();
		
	}

	@Override
	public String getName() {
		return "customer-login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
    	
    	// If customer is already logged in, redirect to customer-mainpanel.jsp
        if (session.getAttribute("customerId") != null) {
        	return "customer-mainpanel.jsp";
        }
        
        // If employee is already logged in, redirect to employee-mainpanel.jsp
        if (session.getAttribute("employeeUserName") != null) {
        	return "employee-mainpanel.jsp";
        }
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	LoginForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "index.jsp";
	        }
	        
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "index.jsp";
	        }
	        
	        CustomerBean customer = customerDAO.read(form.getUserName());
	        
	        if (customer == null) {
	            errors.add("Incorrect/Invalid Customer Username");
	            return "index.jsp";
	        }
	        
	        //System.out.println(customer.checkPassword(form.getPassword()));
	        //System.out.println(customer.getSalt());
	        if (!customer.checkPassword(form.getPassword())) {
	            errors.add("Incorrect/Invalid Password");
	            return "index.jsp";
	        }
	        
	        // Attach (this copy of) the user bean to the session
	        int customerId = customer.getCustomerId();
	        session.setAttribute("customerId", customerId);
	        Date lastTradeDate = transactionDAO.getCustomerLastTradeDate(customerId);
			customer.setLastTradeDate(lastTradeDate);
			session.setAttribute("firstname", customer.getFirstName());
			session.setAttribute("lastname", customer.getLastName());
			
			PositionBean[] positionList = positionDAO.getCustomerPortfolio(customerId);
			request.setAttribute("positionList", positionList);
	        
	        // If redirectTo is null, redirect to the action
			return "customer-mainpanel.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (MyDAOException e){
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}

}
