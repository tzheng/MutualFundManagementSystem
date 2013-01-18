/**
 * @author Team Snipers (Team 1)
 * Jan 17, 2013
 */

package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;

import model.MyDAOException;

public class Model {
	
	private EmployeeDAO employeeDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			
			employeeDAO = new EmployeeDAO(pool, "employee");
		} catch (MyDAOException e) {
			throw new ServletException(e);
		}
	}
	
	public EmployeeDAO getEmployeeDAO() { return employeeDAO;}
}
