package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import formbean.LoginForm;

public class CustomerLoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private CustomerDAO customerDAO;
	
	public CustomerLoginAction(Model model){
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "customer-login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
    	
    	// If customer is already logged in, redirect to customer-mainpanel.jsp
        if (session.getAttribute("customer") != null) {
        	return "customer-mainpanel.jsp";
        }
        
        // If employee is already logged in, redirect to employee-mainpanel.jsp
        if (session.getAttribute("employee") != null) {
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
	            errors.add("Customer's userName not found");
	            return "index.jsp";
	        }
	        
	        if (!customer.getPassword().equals(form.getPassword())) {
	            errors.add("Customer's password is incorrect");
	            return "index.jsp";
	        }
	        
	        // Attach (this copy of) the user bean to the session
	        session.setAttribute("customer", customer);
	        
	        // If redirectTo is null, redirect to the "todolist" action
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
