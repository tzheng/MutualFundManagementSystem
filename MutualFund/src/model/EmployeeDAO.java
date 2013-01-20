/**
 * @author Team Snipers (Team 1)
 * Jan 17, 2013
 */

package model;

import java.sql.*;

import databean.EmployeeBean;

public class EmployeeDAO extends BaseDAO {
	
	public EmployeeDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	// read employee give userName
	public EmployeeBean read(String userName) throws MyDAOException{
		Connection con = null;
        try {
        	con = getConnection();

        	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE userName=?");
        	pstmt.setString(2, userName);
        	ResultSet rs = pstmt.executeQuery();
        	
        	EmployeeBean employee;
        	if (!rs.next()) {
        		employee = null;
        	} else {
        		employee = new EmployeeBean();
        		employee.setUserName(rs.getString("userName"));
        		employee.setPassword(rs.getString("password"));
        		employee.setFirstName(rs.getString("firstName"));
        		employee.setLastName(rs.getString("lastName"));
        		
        	}
        	
        	rs.close();
        	pstmt.close();
        	releaseConnection(con);
            return employee;
            
        } catch (Exception e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
	
	public void createTable() throws MyDAOException{
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(
            		"CREATE TABLE " + tableName + 
            		"(userName VARCHAR(255) NOT NULL," +
            		"firstName VARCHAR(255) NULL ,"+
            		"lastName VARCHAR(255) NULL ,"+
            		"password VARCHAR(255) NOT NULL ," +
            		"PRIMARY KEY (userName) );");
            stmt.close();
        	
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
	
	//Method to create Employee
	public void create(EmployeeBean employee) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName
												+ " (username, password, firstName, lastName) VALUES (?,?,?,?)");
			
			
			pstmt.setString(1, employee.getUserName());
			pstmt.setString(2, employee.getPassword());
			pstmt.setString(3, employee.getFirstName());
			pstmt.setString(4, employee.getLastName());
			
			int count = pstmt.executeUpdate();
			if (count != 1) throw new SQLException("Insert updated "+count+" rows");
			
			pstmt.close();
			releaseConnection(con);
			
		} catch (SQLException e) {
			throw new MyDAOException(e);
		}
	}
	
	// Method to Read employee info from the database
	public EmployeeBean lookup(String username) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE userName=?");
			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery();
			
			EmployeeBean employee;
			if (!rs.next()) {
				employee = null;
			} else {
				employee = new EmployeeBean();
				employee.setUserName(rs.getString("userName"));
				employee.setPassword(rs.getString("password"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setLastName(rs.getString("lastName"));
			}
			
			rs.close();
			pstmt.close();
			releaseConnection(con);
			
			return employee;
		} catch (SQLException e) {
			throw new MyDAOException(e);
		}
	}
}
