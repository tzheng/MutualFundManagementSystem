/**
 * @author Team Snipers (Team 1)
 * Jan 17, 2013
 */

package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import model.MyDAOException;

public class Model {
	
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionHistoryDAO transactionHistoryDAO; 
	
	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			employeeDAO = new EmployeeDAO(jdbcDriver, jdbcURL, "employee");
			fundDAO = new FundDAO(jdbcDriver, jdbcURL, "fund");
			fundPriceHistoryDAO = new FundPriceHistoryDAO(jdbcDriver, jdbcURL, "pricehistory");
			transactionHistoryDAO = new TransactionHistoryDAO(jdbcDriver, jdbcURL, ""); //don't need to create any table. 
			
		} catch (MyDAOException e) {
			throw new ServletException(e);
		}
	}
	
	public EmployeeDAO getEmployeeDAO() { return employeeDAO;}
	public FundDAO getFundDAO() { return fundDAO;}
	public FundPriceHistoryDAO getFundPriceHistoryDAO() { return fundPriceHistoryDAO; }
	public TransactionHistoryDAO getTransactionHistoryDAO() { return transactionHistoryDAO; }
}
