package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.CustomerDAO;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import formbean.CreateAccountForm;


public class CreateAccountAction extends Action {

	private FormBeanFactory<CreateAccountForm> createAccountFormFactory = FormBeanFactory.getInstance(CreateAccountForm.class);
    
	private CustomerDAO customerDAO;
	
	public CreateAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}
	
	@Override
	public String getName() {
		return "create-account.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
        	CreateAccountForm form = createAccountFormFactory.create(request);
	        request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return "create-account.jsp";
	        }
	
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "create-account.jsp";
	        }
	
	        
	        CustomerBean customer = customerDAO.read(form.getUserName());
	        
	       	if (customer != null) {
	       		errors.add("Existing User Name");
                return "create-account.jsp";
	       	}
	       	
	       	customer = new CustomerBean();
	       	customer.setUserName(form.getUserName());
	       	customer.setFirstName(form.getFirstName());
	       	customer.setLastName(form.getLastName());
	       	customer.setPassword(form.getPassword());
	       	customer.setAddrLine1(form.getAddrLine1());
	       	customer.setAddrLine2(form.getAddrLine2());
	       	customer.setCity(form.getCity());
	       	customer.setState(form.getState());
	       	customer.setZip(form.getZipAsInt());
	       	customerDAO.create(customer);
        
//	        HttpSession session = request.getSession(false);
	        session.setAttribute("customer",customer);
	
			return "customer-mainpanel.jsp";

        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
        
	}

}
