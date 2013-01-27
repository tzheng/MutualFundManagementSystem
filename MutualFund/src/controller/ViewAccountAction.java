/**
 * @author Team Snipers (Team 1)
 * Jan 19, 2013
 */

package controller;

import java.text.DecimalFormat;
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
	private FundPriceHistoryDAO pricehistoryDAO;
	private FundDAO fundDAO;

	public ViewAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() { return "view-account.do"; }


	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);


		try {

			int customerId = (Integer) request.getSession(false).getAttribute("customerId");
			CustomerBean customer = customerDAO.read(customerId);



			request.setAttribute("customer", customer);


			
			PositionBean[] userPosition = positionDAO.getCustomerPortfolio(customer.getCustomerId());
		
			
			FundValueBean [] fundValue = new FundValueBean[userPosition.length];
			
			for (int i = 0; i< userPosition.length; i++){
				PositionBean temp = userPosition[i];
				fundValue[i] = new FundValueBean();
				fundValue[i].setFundId(userPosition[i].getFundId());
				fundValue[i].setShares(userPosition[i].getShares());
				//double price = pricehistoryDAO.getLastTradingPrice(userPosition[i].getFundId());
				FundBean fundBean = fundDAO.read(temp.getFundName());
				fundValue[i].setFundName(fundBean.getName());
				//FundPriceHistoryBean [] fundpriceHistory = pricehistoryDAO.getFundPriceHistory(temp.getFundId());
						
			
//				fundValue[i].setLastTradingDate(fundpriceHistory[i].getPrice_date());
//				fundValue[i].setLastTradingPrice(fundpriceHistory[i].getPrice());
				//DecimalFormat formatter = new DecimalFormat("#0.00");
				//fundValue[i].setLastTradingPrice(formatter.format(price));
				//fundValue[i].setValue(price*temp.getShares());
				
//				FundPriceHistoryBean price = pricehistoryDAO.getLastTradingPrice(temp.getFundId());
//				fundValue[i].setLastTradingPrice(price.getPrice());
				
			

		
			}



			request.setAttribute("fundvalue",fundValue);
			

			return "customer-viewaccount.jsp";

		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}	
