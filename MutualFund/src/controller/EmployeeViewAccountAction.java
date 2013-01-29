package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.MyDAOException;
import model.PositionDAO;
import model.TransactionDAO;
import model.TransactionHistoryDAO;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.FundBean;
import databean.FundPriceHistoryBean;
import databean.FundValueBean;
import databean.PositionBean;
import formbean.CustomerIdForm;

public class EmployeeViewAccountAction extends Action {
	private FormBeanFactory<CustomerIdForm> formBeanFactory = FormBeanFactory.getInstance(CustomerIdForm.class);
	
	private TransactionHistoryDAO transactionHistoryDAO;
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	
	public EmployeeViewAccountAction(Model model) {
		transactionHistoryDAO = model.getTransactionHistoryDAO();
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		transactionDAO = model.getTransactionDAO();
		
	}
	
	public String getName() { return "employeeviewaccount.do";}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			if (request.getParameter("username") != null) {
				
				CustomerIdForm form = formBeanFactory.create(request);
				request.setAttribute("form", form);
				
				//get all customer lists, in case of user not found
				CustomerBean[] customerlist = customerDAO.getAllCustomers();
				if (customerlist != null) {
					request.setAttribute("customerlist", customerlist);
				}
				else {
					request.setAttribute("customerlist", null);
				}
				
				if (!form.isPresent()) {
		            return "employee-viewcustomer.jsp";
		        }
				
				// add error validation
				errors.addAll(form.getValidationErrors());
				if (errors.size() != 0) {
					return "employee-viewcustomer.jsp";
				}
				
				CustomerBean customer = customerDAO.read(form.getUsername());
				if (customer == null) {
					errors.add("User does not exist!");
					return "employee-viewcustomer.jsp";
				}
				
				//if user found, set customer list empty, show detail info of specified customer
				request.setAttribute("customerlist", null);
				
				int customerId = customer.getCustomerId();

				request.setAttribute("customer", customer);
				DecimalFormat formatter = new DecimalFormat("#,##0.00");
				request.setAttribute("cash",formatter.format(customer.getCash()));
				
				Date lastTradingDate = transactionDAO.getCustomerLastTradeDate(customerId);
				if (lastTradingDate != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/yyyy");
					request.setAttribute("lastTradingDate", sdf.format(lastTradingDate));
				} else {
					request.setAttribute("lastTradingDate", null);
				}
				
				// get user's fund list and position
				PositionBean[] userPosition = positionDAO.getCustomerPortfolio(customerId);
				
				FundValueBean [] fundValue = new FundValueBean[userPosition.length];
				for (int i = 0; i< userPosition.length; i++){
					PositionBean temp = userPosition[i];
					
					fundValue[i] = new FundValueBean();
					fundValue[i].setFundId(userPosition[i].getFundId());
					double shares = userPosition[i].getShares();
					DecimalFormat formatter1 = new DecimalFormat("#,##0.000");
					fundValue[i].setShares(formatter1.format(shares));
					FundBean fundBean = fundDAO.read(temp.getFundName());
					fundValue[i].setFundName(fundBean.getName());
					if(fundPriceHistoryDAO.getLastTrading(userPosition[i].getFundId())!= null){
						FundPriceHistoryBean history = fundPriceHistoryDAO.getLastTrading(userPosition[i].getFundId());
						fundValue[i].setLastTradingDate(history.getPrice_date());
						double price = history.getPrice();
						formatter = new DecimalFormat("#0.00");
						fundValue[i].setLastTradingPrice(formatter.format(price));
						double value = userPosition[i].getShares()*price;
						fundValue[i].setValue(formatter.format(value));
					}
			
				}

				request.setAttribute("fundvalue",fundValue);
				
			}
			else {
				CustomerBean[] customerlist = customerDAO.getAllCustomers();
				if (customerlist != null) {
					request.setAttribute("customerlist", customerlist);
				}
				else {
					request.setAttribute("customerlist", null);
				}
			}
			
			return "employee-viewcustomer.jsp";		
		}catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "employee-viewcustomer.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "employee-viewcustomer.jsp";
		}
		
	}
}

	

		
