package controller;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.FundBean;
import databean.FundGeneralInfoBean;
import databean.FundPriceHistoryBean;
import formbean.BuyFundForm;

import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionDAO;

public class CustomerBuyFundAction extends Action {
	private FormBeanFactory<BuyFundForm> formBeanFactory = FormBeanFactory.getInstance(BuyFundForm.class);
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	
	public CustomerBuyFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() { return "customer-buyfund.do"; }
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		List<String> successes = new ArrayList<String>();
		request.setAttribute("successes", successes);
		
		try {
			//user can reach this page from other page, for example, user can click "BUY MORE FUND" in mainpanel
			String getFundName = request.getParameter("getFundName");
			if (getFundName != null) request.setAttribute("getFundName", getFundName);
			
			//get full fund list, allows customer to choose
			FundBean[] fundlist = fundDAO.readAllFunds();
			FundGeneralInfoBean[] fundGeneralList = new FundGeneralInfoBean[fundlist.length];
			for (int i = 0; i<fundlist.length; i++) {
				fundGeneralList[i] = new FundGeneralInfoBean();
				fundGeneralList[i].setFundId(fundlist[i].getFundId());
				fundGeneralList[i].setName(fundlist[i].getName());
				fundGeneralList[i].setSymbol(fundlist[i].getSymbol());
				if (fundPriceHistoryDAO.getLastTrading(fundlist[i].getFundId())!= null) {
					FundPriceHistoryBean history = fundPriceHistoryDAO.getLastTrading(fundlist[i].getFundId());
					fundGeneralList[i].setLastTradingDate(history.getPrice_date());
					double price = history.getPrice();
					DecimalFormat formatter = new DecimalFormat("#0.00");
					fundGeneralList[i].setLastTradingPrice(formatter.format(price));
				}
			}
			request.setAttribute("fundGeneralList", fundGeneralList);
			
			// get customer id from session, and read customer information
			int customerId = (Integer) request.getSession(false).getAttribute("customerId");
			CustomerBean customer = customerDAO.read(customerId);
			DecimalFormat formatter = new DecimalFormat("#,##0.00");
			request.setAttribute("cash", formatter.format(customer.getCash()));
			
			BuyFundForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			if (!form.isPresent()) {
				return "customer-buyfund.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "customer-buyfund.jsp";
			}
			
			
			FundBean fund = fundDAO.read(form.getFundName());
			if (fund == null) {
				errors.add("Fund does not exist!");
				return "customer-buyfund.jsp";
			}
			
			//now we have fund, we have amount, we need to check whether customer has enough money
			double amount = form.getAmountAsDouble();
			double cash = customer.getCash();
			formatter = new DecimalFormat("#,##0.00");
			if (amount > cash) {
				errors.add("Amount is more than your cash! Please enter a number less than: " + formatter.format(cash));
				return "customer-buyfund.jsp";
			}
			
			//insert new transaction to database
			transactionDAO.buyFund(customerId, fund.getFundId(), amount);
			successes.add("Thank you! Transacion will be processed after transaction day!");
			return "customer-buyfund.jsp";
			
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}
}
