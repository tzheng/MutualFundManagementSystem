package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;

import basic.FormatNormalization;

import databean.FundBean;
import databean.FundGeneralInfoBean;
import databean.FundPriceHistoryBean;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.MyDAOException;

public class CustomerResearchFundAction extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	public CustomerResearchFundAction(Model model){
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	@Override
	public String getName() {
		return "customer-research-fund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		String temp = request.getParameter("fundId");
		
		Integer fundId = null;
		if(temp != null){
			 fundId = Integer.parseInt(temp);
		}
		
		
		
		
		
		try{
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
					//DecimalFormat formatter = new DecimalFormat("#0.00");
					fundGeneralList[i].setLastTradingPrice(FormatNormalization.formatterForFundPrice.format(price));
				}
			}
			
			request.setAttribute("fundGeneralList", fundGeneralList);
			
			if(fundId != null){
				//System.out.println("1");
				FundPriceHistoryBean[] fundPriceList = fundPriceHistoryDAO.getFundPriceHistory(fundId.intValue());
				request.setAttribute("fundPriceList", fundPriceList);
			}
			
			return "customer-researchfund.jsp";
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}
