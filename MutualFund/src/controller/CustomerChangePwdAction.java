package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;

import formbean.ChangePasswordForm;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;

public class CustomerChangePwdAction extends Action {
	private FormBeanFactory<ChangePasswordForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePasswordForm.class);

	private CustomerDAO customerDAO;

	public CustomerChangePwdAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "customer-change-pwd.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		// Set up success list
		List<String> successes = new ArrayList<String>();
		request.setAttribute("successes", successes);

		try {
			ChangePasswordForm form = formBeanFactory.create(request);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "changepwd-customer.jsp";
			}

			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "changepwd-customer.jsp";
			}
			
			
			CustomerBean customer = customerDAO.read((Integer) session.getAttribute("customerId"));
			
			// check old password
			if(!customer.checkPassword(form.getOldPassword())){
				errors.add("Please your correct old password!");
				return "changepwd-customer.jsp";
			}
			
			// change password
			customerDAO.changePassword(customer.getCustomerId(), form.getNewPassword());
			
			// Success
			successes.add("Password updated!");
			return "changepwd-customer.jsp";
		} catch (MyDAOException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}

}
