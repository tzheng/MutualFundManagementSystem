package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;


import databean.CustomerBean;
import databean.EmployeeBean;
import databean.TransactionBean;
import formbean.DepositCheckFormBean;
import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionDAO;
import javax.servlet.http.HttpServletRequest;

public class DepositCheckAction extends Action{

	private FormBeanFactory<DepositCheckFormBean> formBeanFactory = FormBeanFactory.getInstance(DepositCheckFormBean.class);
	private final String actionPage = "employee-depositcheck.jsp";
	private final String successPage = "employee-confirmation.jsp";
	//private final String errorPage = "employee-error.jsp";

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public DepositCheckAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "employee-depositcheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		//EmployeeBean employee = (EmployeeBean) request.getSession().getAttribute("employee");
		//String customerName = request.getParameter("custName");
		
		try {
            DepositCheckFormBean form = formBeanFactory.create(request);
            request.setAttribute("form", form);
            
            if (!form.isPresent()) {
	            return actionPage;
	        }
	
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return actionPage;
            }
            
            CustomerBean customer = customerDAO.read(form.getUserName());
            if (customer == null) {
            	errors.add("Customer does not exist");
            	return actionPage;
            }
            
            transactionDAO.depositCheck(customer.getCustomerId(), form.getAmountAsDouble());
			DecimalFormat formatter = new DecimalFormat("#,##0.00");
			request.setAttribute("message","Your request for Check Deposit of $ " + "<b>" + formatter.format(form.getAmountAsDouble()) + "</b> "+"has been queued as a pending transaction");			
            return successPage;

        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
			return "error.jsp";
        } catch (NumberFormatException e) {
        	errors.add(e.getMessage());
			return "error.jsp";
        }  catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}  

		
	}


}
