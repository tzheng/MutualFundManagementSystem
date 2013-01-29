package controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import databean.FundBean;
import databean.FundGeneralInfoBean;

import formbean.FundForm;


public class CreateFundAction extends Action {

	private FormBeanFactory<FundForm> formBeanFactory = FormBeanFactory
			.getInstance(FundForm.class);

	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	public CreateFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return "createfund.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			FundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			//get a full fund list
			FundGeneralInfoBean[] fundGeneralList = fundPriceHistoryDAO.getAllFundsGeneralInfo();
			request.setAttribute("fundGeneralList", fundGeneralList);
			
			if (!form.isPresent()) {
	            return "employee-createfund.jsp";
	        }
	
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "employee-createfund.jsp";
	        } 
	        //test whether fundname and symbol are existed.
	        FundBean fund = fundDAO.read(form.getFundName());
	        FundBean symbol = fundDAO.readSymbol(form.getSymbol());
	        
	       	if (fund!= null) {
	       		errors.add("Existing Fund Name");
	       		return "employee-createfund.jsp";
	       	}
	       	if (symbol!=null){
	       		errors.add("Existing Symbol");
	       		return "employee-createfund.jsp";
	       	}
	        
	       	fund = new FundBean();
			fund.setSymbol(form.getSymbol());
			fund.setName(form.getFundName());
			fundDAO.create(fund);
			
			request.setAttribute("message"," Fund Successfully Created. Fund: " + "<b>" + form.getFundName() 
					+ "</b>, Symbol: <b>" + form.getSymbol() + "</b>");
			return "employee-confirmation.jsp";
			
		} catch ( MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		}
	}
}