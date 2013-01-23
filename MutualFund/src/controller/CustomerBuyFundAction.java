package controller;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import databean.FundBean;
import databean.FundGeneralInfoBean;
import databean.FundPriceHistoryBean;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.MyDAOException;

public class CustomerBuyFundAction extends Action {
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	public CustomerBuyFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}
	
	public String getName() { return "customer-buyfund.do"; }
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			
			//get general information of all funds
			FundBean[] fundlist = fundDAO.readAllFunds();
			System.out.println(fundlist.length);
			FundGeneralInfoBean[] fundGeneralList = new FundGeneralInfoBean[fundlist.length];
			for (int i = 0; i<fundlist.length-1; i++) {
				fundGeneralList[i].setFundId(fundlist[i].getFundId());
				fundGeneralList[i].setFundName(fundlist[i].getName());
				fundGeneralList[i].setSymbol(fundlist[i].getSymbol());
				FundPriceHistoryBean history = fundPriceHistoryDAO.getLastTrading(fundlist[i].getFundId());
				fundGeneralList[i].setLastTradingDate(history.getPrice_date());
				double price = history.getPrice();
				DecimalFormat formatter = new DecimalFormat("#0.00");
				fundGeneralList[i].setLastTradingPrice(formatter.format(price));
			}
			request.setAttribute("fundGeneralList", fundGeneralList);
			
			return "customer-buyfund.jsp";
			
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
