package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.EmployeeBean;

import formbean.ChangePasswordForm;

public class EmployeeChangePwdAction extends Action {
	private FormBeanFactory<ChangePasswordForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePasswordForm.class);

	private EmployeeDAO employeeDAO;

	public EmployeeChangePwdAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	@Override
	public String getName() {
		return "employee-change-pwd.do";
	}

	@Override
	public synchronized String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			ChangePasswordForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "changepwd-employee.jsp";
			}

			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "changepwd-employee.jsp";
			}

			EmployeeBean employee = employeeDAO.read((String) session
					.getAttribute("employeeUserName"));

			// check old password
			if (!employee.checkPassword(form.getOldPassword())) {
				errors.add("Incorrect Password!! Please re-enter your current password");
				return "changepwd-customer.jsp";
			}
			
			// change password
			employeeDAO.changePassword(employee.getUserName(), form.getNewPassword());

			// Success
			request.setAttribute("message", "Password changed successfully!");
			return "employee-confirmation.jsp";
		} catch (MyDAOException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}

}
