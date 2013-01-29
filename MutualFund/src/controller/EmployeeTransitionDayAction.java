/**
 * @author Team Snipers (Team 1)
 * Jan 26, 2013
 */

package controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import databean.CustomerBean;
import databean.FundGeneralInfoBean;
import databean.FundPriceHistoryBean;
import databean.PendingTransactionBean;
import databean.PositionBean;
import databean.TransactionBean;
import formbean.TransitionDayForm;

import model.CustomerDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.MyDAOException;
import model.PositionDAO;
import model.TransactionDAO;

public class EmployeeTransitionDayAction extends Action {
	private CustomerDAO customerDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	
	public EmployeeTransitionDayAction(Model model) {
		customerDAO = model.getCustomerDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() { return "employee-transitionday.do"; }
	
	public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
        	DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        	
        	// Get last dates of transaction and fund price history.  Set later one as last trading date.
        	Date lastDateTransaction = transactionDAO.getLastTradingDateOfALLTransactions();
        	Date lastDateFund = fundPriceHistoryDAO.getLastTradingDateOfALLFunds();        	
        	
        	Date lastDate;
        	if (lastDateTransaction == null) {
        		lastDate = lastDateFund;
        	} else if (lastDateFund == null) {
        		lastDate = lastDateTransaction;
        	} else {
        		lastDate = lastDateTransaction.after(lastDateFund) ? lastDateTransaction : lastDateFund;
        	}
        	
        	if (lastDate == null) {
        		request.setAttribute("lastDate", "No last trading day");
        	} else {
        		request.setAttribute("lastDate", df.format(lastDate));
        	}
        	
        	// Set default date as tomorrow if last date exists.  Otherwise, set today as default day.
        	Date defaultDate = lastDate != null ? getTomorrow(lastDate) : new Date();
        	request.setAttribute("defaultDate", df.format(defaultDate));
        	
        	//get full fund list, allows customer to choose
        	FundGeneralInfoBean[] fundGeneralList = fundPriceHistoryDAO.getAllFundsGeneralInfo();
			request.setAttribute("fundGeneralList", fundGeneralList);
			int len = fundGeneralList == null ? 0: fundGeneralList.length;
			request.setAttribute("fundListLength", len);
        	
	    	TransitionDayForm form = new TransitionDayForm(request);
	        request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "employee-transitionday.jsp";
	        }
	        
	        // Remain prices entered before
	        String[] formPrice = form.getClosingPrice();
	        if (formPrice != null) {
	        	for (int i = 0; i < len; i++) {
	        		fundGeneralList[i].setSpecifiedPrice(formPrice[i]);
	        	}
	        }
	        
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "employee-transitionday.jsp";
	        }

	        // Get specified date
	        Date specifiedDate = form.getSpecifiedDate();
	        
	        if (!specifiedDate.after(lastDate)) {
	        	errors.add("Can only choose the date after last trading date.");
	        	return "employee-transitionday.jsp";
	        }
	        
	        // Check whether fund list is empty, if not, update prices.
	        if (!form.isFundListEmpty()) {
	        	// Get fund ids and closing prices from fund list grid.
	        	int[] fundIds = form.getFundIdsAsInteger();
		        double[] closingPrices = form.getClosingPricesAsDouble();
		        
		        // Insert new prices into price history table
		        for (int i = 0; i < len; i++) {
		        	FundPriceHistoryBean bean = new FundPriceHistoryBean();
		        	bean.setFund_id(fundIds[i]);
		        	bean.setPrice(closingPrices[i]);
		        	bean.setPrice_date(specifiedDate);
		        	fundPriceHistoryDAO.create(bean);
		        }
	        }
	        
	        // Process all pending transactions
	        int success = 0;
	        int rejected = 0;
	        PendingTransactionBean bean = getPendingTransactionAllInfo();
	        while (bean != null) {
	        	boolean flag = false;
	        	switch (bean.getTransactionType()) {
					case 1:
						flag = buyFund(bean, specifiedDate);
						break;
					case 2:
						flag = sellFund(bean, specifiedDate);
						break;
					case 3:
						flag = requestCheck(bean, specifiedDate);
						break;
					case 4:
						flag = depositCheck(bean, specifiedDate);
						break;
	        	}
	        	if (flag) {
	        		success++;
	        	} else {
	        		rejected++;
	        	}
	        	bean = getPendingTransactionAllInfo();
	        }
	        
	        // Display message
	        request.setAttribute("message", "Transition Day finished successfully. " + success + " are processed, " + rejected + " are rejected.");
			return "employee-confirmation.jsp";
        } catch (MyDAOException e) {
        	errors.add(e.getMessage());
        	return "employee-transitionday.jsp";
        }
    }

	private PendingTransactionBean getPendingTransactionAllInfo() throws MyDAOException {
		PendingTransactionBean bean = transactionDAO.readFirstPendingTransaction();
		if (bean == null) return null;
		
		FundPriceHistoryBean historyBean = fundPriceHistoryDAO.getLastTrading(bean.getFundId());
		if (historyBean != null) {
			bean.setPrice(historyBean.getPrice());
			bean.setExecuteDate(historyBean.getPrice_date());
		}
		
		bean.setOwnedShares(positionDAO.getSinglePosition(bean.getCustomerId(), bean.getFundId()));
		bean.setCash(customerDAO.read(bean.getCustomerId()).getCash());
		return bean;
	}
	
	public boolean buyFund(PendingTransactionBean bean, Date date) throws MyDAOException {
		if (bean.getAmount() > bean.getCash()) {
			transactionDAO.rejectTransaction(date, bean.getTransactionId());
			return false;
		}
		
		// Do calculation
		double boughtShares = divide(bean.getAmount(), bean.getPrice(), 3);
		double totalShares = add(boughtShares, bean.getOwnedShares());
		double cost = multiply(boughtShares, bean.getPrice());
		double remainingCash = subtract(bean.getCash(), cost);
		
		// Update position table
		PositionBean positionBean = new PositionBean();
		positionBean.setCustomerId(bean.getCustomerId());
		positionBean.setFundId(bean.getFundId());
		positionBean.setShares(totalShares);
		if (positionDAO.read(bean.getCustomerId(), bean.getFundId()) != null) {
			positionDAO.update(positionBean);
		} else {
			positionDAO.create(positionBean);
		}
		
		// Update customer table
		CustomerBean customerBean = new CustomerBean();
		customerBean.setCustomerId(bean.getCustomerId());
		customerBean.setCash(remainingCash);
		customerDAO.updateCash(customerBean);
		
		// Update transaction table
		TransactionBean transactionBean = new TransactionBean();
		transactionBean.setTransactionId(bean.getTransactionId());
		transactionBean.setExecuteDate(date);
		transactionBean.setShares(boughtShares);
		transactionBean.setSharePrice(bean.getPrice());
		transactionBean.setAmount(cost);
		transactionDAO.processFundTransaction(transactionBean);
		
		return true;
	}
	
	public boolean sellFund(PendingTransactionBean bean, Date date) throws MyDAOException {
		if (bean.getToSellShares() > bean.getOwnedShares()) {
			transactionDAO.rejectTransaction(date, bean.getTransactionId());
			return false;
		}
		
		// Do calculation
		double remainingShares = subtract(bean.getOwnedShares(), bean.getToSellShares());
		double revenue = multiply(bean.getToSellShares(), bean.getPrice());
		double totalCash = add(bean.getCash(), revenue);
		
		// Update position table
		PositionBean positionBean = new PositionBean();
		positionBean.setCustomerId(bean.getCustomerId());
		positionBean.setFundId(bean.getFundId());
		positionBean.setShares(remainingShares);
		positionDAO.update(positionBean);
		
		// Update customer table
		CustomerBean customerBean = new CustomerBean();
		customerBean.setCustomerId(bean.getCustomerId());
		customerBean.setCash(totalCash);
		customerDAO.updateCash(customerBean);
		
		// Update transaction table
		TransactionBean transactionBean = new TransactionBean();
		transactionBean.setTransactionId(bean.getTransactionId());
		transactionBean.setExecuteDate(date);
		transactionBean.setShares(bean.getToSellShares());
		transactionBean.setSharePrice(bean.getPrice());
		transactionBean.setAmount(revenue);
		transactionDAO.processFundTransaction(transactionBean);
		
		return true;
	}
	
	public boolean requestCheck(PendingTransactionBean bean, Date date) throws MyDAOException {
		if (bean.getAmount() > bean.getCash()) {
			transactionDAO.rejectTransaction(date, bean.getTransactionId());
			return false;
		}
		
		// Do calculation
		double remainingCash = subtract(bean.getCash(), bean.getAmount());
		
		// Update customer table
		CustomerBean customerBean = new CustomerBean();
		customerBean.setCustomerId(bean.getCustomerId());
		customerBean.setCash(remainingCash);
		customerDAO.updateCash(customerBean);
		
		// Update transaction table
		TransactionBean transactionBean = new TransactionBean();
		transactionBean.setTransactionId(bean.getTransactionId());
		transactionBean.setExecuteDate(date);
		transactionBean.setAmount(bean.getAmount());
		transactionDAO.processCheckTransaction(transactionBean);
		
		return true;
	}
	
	public boolean depositCheck(PendingTransactionBean bean, Date date) throws MyDAOException {		
		// Do calculation
		double totalCash = add(bean.getCash(), bean.getAmount());
		
		// Update customer table
		CustomerBean customerBean = new CustomerBean();
		customerBean.setCustomerId(bean.getCustomerId());
		customerBean.setCash(totalCash);
		customerDAO.updateCash(customerBean);
		
		// Update transaction table
		TransactionBean transactionBean = new TransactionBean();
		transactionBean.setTransactionId(bean.getTransactionId());
		transactionBean.setExecuteDate(date);
		transactionBean.setAmount(bean.getAmount());
		transactionDAO.processCheckTransaction(transactionBean);
		
		return true;
	}
	
	public double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	public double subtract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	public double multiply(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	public double divide(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Date getTomorrow(Date today) {
		if (today == null) return new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}
}
