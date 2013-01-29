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
			FundGeneralInfoBean[] fundGeneralList = fundPriceHistoryDAO.getAllFundsGeneralInfo();
			request.setAttribute("fundGeneralList", fundGeneralList);
			
			request.setAttribute("fundGeneralList", fundGeneralList);
			
			if(fundId != null){
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
