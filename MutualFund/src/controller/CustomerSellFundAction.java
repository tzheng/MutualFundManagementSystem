/**
 * @author Team Snipers (Team 1)
 * Jan 24, 2013
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundDAO;
import model.Model;
import model.MyDAOException;
import model.PositionDAO;
import model.TransactionDAO;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.FundBean;
import databean.PositionBean;

import formbean.SellFundForm;

public class CustomerSellFundAction extends Action {
	private FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory.getInstance(SellFundForm.class);
	
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	
	public CustomerSellFundAction(Model model) {
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() { return "customer-sellfund.do"; }
	
	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			//user can reach this page from other page, for example, user can click "SELL FUND" in mainpanel
			String getFundName = request.getParameter("getFundName");
			if (getFundName != null) request.setAttribute("getFundName", getFundName);
			
			int customerId = (Integer) request.getSession(false).getAttribute("customerId");
        	PositionBean[] positionList = positionDAO.getCustomerPortfolio(customerId);
	        request.setAttribute("positionList",positionList);

			SellFundForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			
			if (!form.isPresent()) {
				return "customer-sellfund.jsp";
			}
			
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) return "customer-sellfund.jsp";

			String fundName = form.getFundName();
			double shares = form.getSharesAsDouble();
			
			FundBean fundBean = fundDAO.read(fundName);
			if (fundBean == null) {
				errors.add("Fund does not exist!");
				return "customer-sellfund.jsp";
			}
			
			int index = findByFundId(positionList, fundBean.getFundId());
			
			if (index == -1) {
				errors.add("Cannot sell the fund you don't have");
				return "customer-sellfund.jsp";
			}
			
			if (shares > positionList[index].getAvailableShares()) {
				errors.add("Cannot sell fund shares more than your available shares");
				return "customer-sellfund.jsp";
			}
			
			transactionDAO.sellFund(customerId, fundBean.getFundId(), shares);
			positionList[index].setAvailableShares(positionList[index].getAvailableShares() - shares);
			positionDAO.update(positionList[index]);
			
	        return "customer-sellfund.jsp";
	 	} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "customer-sellfund.jsp";
	 	} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "customer-sellfund.jsp";
		}
    }
	
	private int findByFundId(PositionBean[] list, int fundId) {
		for (int i = 0; i < list.length; i++) {
			if (list[i].getFundId() == fundId) {
				return i;
			}
		}
		return -1;
	}
}
