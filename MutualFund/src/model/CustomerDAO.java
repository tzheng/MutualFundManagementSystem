/**
 * @author Team Snipers (Team 1)
 * Jan 17, 2013
 */

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databean.CustomerBean;

import model.MyDAOException;

public class CustomerDAO extends BaseDAO {
	int count;
	
	
	public CustomerDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	public synchronized void changePassword(int customerId, String newPassword){
		Connection con = null;
        try {
        	con = getConnection();
        	
        	CustomerBean customer = new CustomerBean();
        	customer.setPassword(newPassword);
        	
        	PreparedStatement pstmt = con.prepareStatement("UPDATE " + tableName + " SET password=? , salt=? WHERE customerId=?");
        	pstmt.setString(1, customer.getPassword());
        	pstmt.setInt(2, customer.getSalt());
        	pstmt.setInt(3, customerId);
        	int count = pstmt.executeUpdate();
        	if (count != 1)
				throw new SQLException("Updated " + count + " rows in customer table when changing password.");
        	con.commit();
        	
        	pstmt.close();
        	releaseConnection(con);    
        } catch (Exception e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	//throw new MyDAOException(e);
        }
	}
	
	public CustomerBean read(int customerId) throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();

        	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE customerId=?");
        	pstmt.setInt(1,customerId);
        	ResultSet rs = pstmt.executeQuery();
        	
        	CustomerBean customer;
        	if (!rs.next()) {
        		customer = null;
        	} else {
        		customer = new CustomerBean();
        		customer.setCustomerId(rs.getInt("customerId"));
        		customer.setUserName(rs.getString("userName"));
        		customer.setDirectPassword(rs.getString("password"));
        		customer.setFirstName(rs.getString("firstName"));
        		customer.setLastName(rs.getString("lastName"));
        		customer.setAddrLine1(rs.getString("addrLine1"));
        		customer.setAddrLine2(rs.getString("addrLine2"));
        		customer.setCity(rs.getString("city"));
        		customer.setState(rs.getString("state"));
        		customer.setZip(rs.getInt("zip"));
        		
        		customer.setCash(rs.getLong("cash")/100.00);//CHANGE

        		customer.setSalt(rs.getInt("salt"));
        		

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
	
	public CustomerBean read(String userName) throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();

        	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE userName=?");
        	pstmt.setString(1, userName);
        	ResultSet rs = pstmt.executeQuery();
        	
        	CustomerBean customer;
        	if (!rs.next()) {
        		customer = null;
        	} else {
        		customer = new CustomerBean();
        		customer.setCustomerId(rs.getInt("customerId"));
        		customer.setUserName(rs.getString("userName"));
        		customer.setDirectPassword(rs.getString("password"));
        		customer.setFirstName(rs.getString("firstName"));
        		customer.setLastName(rs.getString("lastName"));
        		customer.setAddrLine1(rs.getString("addrLine1"));
        		customer.setAddrLine2(rs.getString("addrLine2"));
        		customer.setCity(rs.getString("city"));
        		customer.setState(rs.getString("state"));
        		customer.setZip(rs.getInt("zip"));

        		
        		customer.setCash(rs.getLong("cash")/100.00);//CHANGE

        		customer.setSalt(rs.getInt("salt"));
        		//CHANGE

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
	
	public void create(CustomerBean customer) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
        	con.setAutoCommit(false);
        	
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO " + tableName +
					" (userName, firstName, lastName, password, addrLine1, addrLine2, city, state, zip, salt, cash)" +
					" VALUES (?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setString(1, customer.getUserName());
			pstmt.setString(2, customer.getFirstName());
			pstmt.setString(3, customer.getLastName());
			pstmt.setString(4, customer.getPassword());
			pstmt.setString(5, customer.getAddrLine1());
			pstmt.setString(6, customer.getAddrLine2());
			pstmt.setString(7, customer.getCity());
			pstmt.setString(8, customer.getState());
			pstmt.setInt(9, customer.getZip());

			pstmt.setInt(10, customer.getSalt());
			
			long cashL = Math.round(customer.getCash() * 100);
			pstmt.setLong(11,cashL); // CHANGE

			
			int count = pstmt.executeUpdate();
        	if (count != 1) throw new SQLException("Insert updated "+count+" rows");
			
        	pstmt.close();
        	
        	con.commit();
            con.setAutoCommit(true);
        	releaseConnection(con);
			
		} catch (Exception e) {
            try {
            	if (con != null) {
            		con.rollback();
            		con.close();
            	}
            } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
	
	public void updateCash(CustomerBean bean) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);

			PreparedStatement pstmt = con
					.prepareStatement(
							"UPDATE " + tableName + 
							" SET cash = ?" +
							" WHERE customerId = ?");

			long cashLong = Math.round(bean.getCash() * 100.00);
			pstmt.setLong(1, cashLong);
			pstmt.setInt(2, bean.getCustomerId());
			int count = pstmt.executeUpdate();
			if (count != 1)
				throw new SQLException("Updated " + count + " rows in customer table");

			pstmt.close();

			con.commit();
			con.setAutoCommit(true);
			releaseConnection(con);

		} catch (SQLException e) {
			// If this exception is caused by database-related problem, roll
			// back and close this connection.
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (SQLException e2) { /* ignore */
			}
			throw new MyDAOException(e);
		} finally {
			// If this exception is caused by illegal update action, roll back
			// but don't close this connection.
			try {
				if (con != null) {
					con.rollback();
					con.setAutoCommit(true);
					releaseConnection(con);
				}
			} catch (SQLException e2) { /* ignore */
			}
		}
	}
	
		public CustomerBean[] getAllCustomers() throws Exception {
		Connection con = null;
        try {
        	con = getConnection();

        	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName);
        	ResultSet rs = pstmt.executeQuery();
        	
        	List<CustomerBean> list = new ArrayList<CustomerBean>();

        	while (rs.next()) {
        		CustomerBean customer = new CustomerBean();
        		customer.setUserName(rs.getString("userName"));
        		customer.setCustomerId(rs.getInt("customerId"));
        		customer.setFirstName(rs.getString("firstName"));
        		customer.setLastName(rs.getString("lastName"));
        		list.add(customer);
        	}
	       	
	        	rs.close();
	        	pstmt.close();
	        	releaseConnection(con);
	            return list.toArray(new CustomerBean[list.size()]);
	            
	        } catch (Exception e) {
	            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
	        	throw new Exception(e);
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
	            		"salt INT(11) DEFAULT 0 ," +
	            		"cash BIGINT(32) DEFAULT 0," +
	            		"PRIMARY KEY (customerId) );");
	            stmt.close();
	        	
	        	releaseConnection(con);

	        } catch (SQLException e) {
	            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
	        	throw new MyDAOException(e);
	        }
	    }
}
