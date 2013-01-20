/**
 * @author Team Snipers (Team 1)
 * Jan 17, 2013
 */

package model;

import java.sql.*;
import java.util.*;

import databean.CustomerBean;


public class CustomerDAO extends BaseDAO {
	
	
	public CustomerDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	public CustomerBean read(String customerId) throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();

        	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE customerId=?");
        	pstmt.setString(1,customerId);
        	ResultSet rs = pstmt.executeQuery();
        	
        	CustomerBean customer;
        	if (!rs.next()) {
        		customer = null;
        	} else {
        		customer = new CustomerBean();
        		customer.setCustomerId(rs.getInt("customerId"));
        		customer.setUserName(rs.getString("userName"));
        		customer.setPassword(rs.getString("password"));
        		customer.setFirstName(rs.getString("firstName"));
        		customer.setLastName(rs.getString("lastName"));
        		customer.setAddrLine1(rs.getString("addrLine1"));
        		customer.setAddrLine2(rs.getString("addrLine2"));
        		customer.setCity(rs.getString("city"));
        		customer.setState(rs.getString("state"));
        		customer.setZip(rs.getInt("zip"));
        		customer.setCash(rs.getDouble("cash"));
        	}
        	
        	rs.close();
        	pstmt.close();
        	releaseConnection(con);
            return customer;
            
        } catch (Exception e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
	
	
	// Method to Create New Table if Table Doesn't exist
	protected void createTable() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(
            		"CREATE TABLE " + tableName + 
            		" (customerId INT NOT NULL AUTO_INCREMENT ," +
            		"userName VARCHAR(255) NOT NULL," +
            		"firstName VARCHAR(255) NULL ,"+
            		"lastName VARCHAR(255) NULL ,"+
            		"password VARCHAR(255) NOT NULL ," +
            		"addrLine1 VARCHAR(255) NULL ," +
            		"addrLine2 VARCHAR(255) NULL ," +
            		"city VARCHAR(255) NULL ," +
            		"state VARCHAR(255) NULL ," +
            		"zip INT(11) NULL ," +
            		"cash DOUBLE(255,2) NOT NULL ," +
            		"PRIMARY KEY (customer_id) );");
            stmt.close();
        	
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
    }
}

