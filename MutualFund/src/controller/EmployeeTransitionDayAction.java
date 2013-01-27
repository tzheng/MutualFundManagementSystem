/**
 * @author Team Snipers (Team 1)
 * Jan 26, 2013
 */

package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databean.FundBean;
import databean.FundGeneralInfoBean;
import databean.FundPriceHistoryBean;
import formbean.TransitionDayForm;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionDAO;

public class EmployeeTransitionDayAction extends Action {
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	
	public EmployeeTransitionDayAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() { return "employee-transitionday.do"; }
	
	public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        System.out.println("123");
        try {
        	Date lastDate = fundPriceHistoryDAO.getLastTradingDateOfALLFunds();
            request.setAttribute("lastDate", lastDate);
            System.out.println("abc");
            
        	//get full fund list, allows customer to choose
			FundBean[] fundlist = fundDAO.readAllFunds();
			request.setAttribute("fundListLength", fundlist.length);
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
        	
	    	TransitionDayForm form = new TransitionDayForm(request);
	        request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "employee-transitionday.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "employee-transitionday.jsp";
	        }

	        // Get specified date
	        Date specifiedDate = form.getSpecifiedDate();
	        request.setAttribute("specifiedDate", form.getDate());
	        
	        if (!specifiedDate.after(lastDate)) {
	        	errors.add("Cannot choose date before last trading date.");
	        	return "employee-transitionday.jsp";
	        }

	        // Get fund ids and closing prices from fund list grid.
	        int[] fundIds = form.getFundIdsAsInteger();
	        double[] closingPrices = form.getClosingPricesAsDouble();
	        
	        for (int i = 0; i < form.getFundListLength(); i++) {
	        	FundPriceHistoryBean bean = new FundPriceHistoryBean();
	        	bean.setFund_id(fundIds[i]);
	        	bean.setPrice(closingPrices[i]);
	        	bean.setPrice_date(specifiedDate);
	        	fundPriceHistoryDAO.create(bean);
	        }
	        
	        // Display message
	        request.setAttribute("message", "Closing prices have been set up.");
			return "employee-confirmation.jsp";
        } catch (MyDAOException e) {
        	errors.add(e.getMessage());
        	return "employee-transitionday.jsp";
        }
    }
}
