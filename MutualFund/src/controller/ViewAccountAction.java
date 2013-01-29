/**
 * @author Team Snipers (Team 1)
 * Jan 19, 2013
 */

package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.MyDAOException;
import model.CustomerDAO;
import model.PositionDAO;
import model.TransactionDAO;
import databean.CustomerBean;
import databean.FundBean;
import databean.FundPriceHistoryBean;
import databean.FundValueBean;
import databean.PositionBean;

public class ViewAccountAction extends Action {
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private FundDAO fundDAO;

	public ViewAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() { return "view-account.do"; }


	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);

		List<String> successes = new ArrayList<String>();
		request.setAttribute("successes", successes);
		
		try {

			int customerId = (Integer) request.getSession(false).getAttribute("customerId");
			CustomerBean customer = customerDAO.read(customerId);
			DecimalFormat formatter = new DecimalFormat("#,##0.00");
			request.setAttribute("cashBalance",formatter.format(customer.getCash()));

			//set current balance for customer.
			DecimalFormat format = new DecimalFormat("#,##0.00");
			request.setAttribute("currentBalance", format.format(customer.getCash()));
			// get the last trading date of this customer
			Date lastTradingDate = transactionDAO.getCustomerLastTradeDate(customerId);
			if (lastTradingDate != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				request.setAttribute("lastTradingDate", sdf.format(lastTradingDate));
				//System.out.println(sdf.format(lastTradingDate));
				//get customer's Rejected transactions of his/her Last Trading Day
				int processed = transactionDAO.getCustomerTransactionNum(customerId, 1, lastTradingDate);
				request.setAttribute("processedNumber", processed);
				//get customer's Processed transactions of his/her Last Trading Day
				int rejected = transactionDAO.getCustomerTransactionNum(customerId, -1, lastTradingDate);
				request.setAttribute("rejectedNumber", rejected);
				
				double processedPercent = (double) processed / (processed + rejected);
				double rejectedPercent = 1 - processedPercent;
				request.setAttribute("pPercent", Math.round(processedPercent * 100));
				request.setAttribute("rPercent", Math.round(rejectedPercent * 100));
			} else {
				request.setAttribute("lastTradingDate", null);
				request.setAttribute("processedNumber", null);
				//get customer's Processed transactions of his/her Last Trading Day
				request.setAttribute("rejectedNumber", null);
			}
			
			request.setAttribute("customer", customer);
			
			PositionBean[] userPosition = positionDAO.getCustomerPortfolio(customer.getCustomerId());
			
			FundValueBean[] fundValue = new FundValueBean[userPosition.length];
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
					formatter = new DecimalFormat("#,##0.00");
					fundValue[i].setLastTradingPrice(formatter.format(price));
					double value = userPosition[i].getShares()*price;
					fundValue[i].setValue(formatter.format(value));
				}
		
			}

			request.setAttribute("fundvalue",fundValue);

			return "customer-viewaccount.jsp";
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}	
