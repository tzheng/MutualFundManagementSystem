/**
 * @author Team Snipers (Team 1)
 * Jan 19, 2013
 */

package controller;

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
			int customerId = 1;
			//int customerId = (Integer) request.getSession(false).getAttribute("customerId");
			CustomerBean customer = customerDAO.read(customerId);
			Date lastTradeDate = transactionDAO.getCustomerLastTradeDate(customerId);
			customer.setLastTradeDate(lastTradeDate);

			request.setAttribute("customer", customer);

			PositionBean[] positionList = positionDAO.getCustomerPortfolio(customerId);
			request.setAttribute("positionList", positionList);


			//List<PositionBean> userPosition = new ArrayList<PositionBean>();
			
			PositionBean[] userPosition = positionDAO.getCustomerPortfolio(customerId);
			CustomerBean customerInfo = customerDAO.read(customerId);
			FundValueBean [] fundValue = new FundValueBean[userPosition.length];
			for (int i = 0; i< userPosition.length; i++){
				
				fundValue[i] = new FundValueBean();
				fundValue[i].setFundId(userPosition[i].getFundId());
				fundValue[i].setShares(userPosition[i].getShares());
				

//				PositionBean temp = userPosition[i];
//				double price = pricehistoryDAO.getLastTradingPrice(temp.getFundId());
//				Date date = pricehistoryDAO.getLastTradingDate(temp.getFundId());
//				FundBean fundBean = fundDAO.read(temp.getFundName());
//				FundValueBean current = new FundValueBean();
//				current.setLastTradingPrice(price);
//				current.setFundName(fundBean.getName());
//				current.setShares(temp.getShares());
//				current.setLastTradingDate(date);
//				current.setValue(price*temp.getShares());
			}

			request.setAttribute("fundvalue",fundValue);
			return "customer-viewaccount.jsp";




		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
