package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;

import formbean.ResetCustomerPwdForm;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;

public class EmployeeResetCustomerPwdAction extends Action {
	private FormBeanFactory<ResetCustomerPwdForm> formBeanFactory = FormBeanFactory
			.getInstance(ResetCustomerPwdForm.class);

	private CustomerDAO customerDAO;

	public EmployeeResetCustomerPwdAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "employee-reset-customer-pwd.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		// Set up success list
		List<String> successes = new ArrayList<String>();
		request.setAttribute("successes", successes);

		try {
			ResetCustomerPwdForm form = formBeanFactory.create(request);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "employee-resetcustomerpswd.jsp";
			}

			
			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "employee-resetcustomerpswd.jsp";
			}
			
			CustomerBean customer = customerDAO.read(form.getUserName());
			
			customerDAO.changePassword(customer.getCustomerId(), form.getNewPassword());

			// Success
			successes.add("Customer's password updated!");
			return "employee-resetcustomerpswd.jsp";
		} catch (MyDAOException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}
}
