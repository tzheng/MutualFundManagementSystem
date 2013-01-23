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

import databean.CustomerBean;

import model.MyDAOException;

public class CustomerDAO extends BaseDAO {
	
	
	public CustomerDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	public synchronized void changePassword(int customerId, String newPassword){
		Connection con = null;
        try {
        	con = getConnection();
        	
        	CustomerBean customer = new CustomerBean();
        	customer.setPassword(newPassword);
        	
        	PreparedStatement pstmt = con.prepareStatement("UPDATE " + tableName + " SET password=? WHERE customerId=?");
        	pstmt.setString(1, customer.getPassword());
        	pstmt.setInt(2, customerId);
        	int rs = pstmt.executeUpdate();
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
        		customer.setSalt(rs.getInt("salt"));
        		customer.setCash(rs.getDouble("cash")); //CHANGE
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
        		customer.setSalt(rs.getInt("salt"));
        		customer.setCash(rs.getDouble("cash")); //CHANGE
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
			pstmt.setDouble(11, customer.getCash()); // CHANGE
			
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
	
//	public CustomerBean[] getUsers() throws Exception {
//		Connection con = null;
//        try {
//        	con = getConnection();
//
//        	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName);
//        	ResultSet rs = pstmt.executeQuery();
//        	
//        	
//        	CustomerBean [] customer = null;
////        	if(count != 0) {
////        		user = new CustomerBean[count];
////        	}
//        	if(rs.first()) {
//        		for(int i=0;i<count;i++) {
//        			customer[i] = new CustomerBean();
//        			customer[i].setCustomerId(rs.getInt("customerId"));
//        			customer[i].setUserName(rs.getString("userName"));
//        			customer[i].setFirstName(rs.getString("firstName"));
//        			customer[i].setLastName(rs.getString("lastName"));
//        			customer[i].setPassword(rs.getString("password"));
//        			customer[i].setAddrLine1(rs.getString("addrLine1"));
//        			customer[i].setAddrLine2(rs.getString("addrLine2"));
//        			customer[i].setCity(rs.getString("city"));
//        			customer[i].setState(rs.getString("state"));
//        			customer[i].setZip(rs.getInt("zip"));
//            		rs.next();
//            	}
//        	}
//        	
//        	rs.close();
//        	pstmt.close();
//        	releaseConnection(con);
//            return customer;
//            
//        } catch (Exception e) {
//            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
//        	throw new Exception(e);
//        }
//	}
	
	public CustomerBean lookup(int customerId) throws Exception{
		Connection con = null;
        try {
        	con = getConnection();

        	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE customerId=?");
        	pstmt.setInt(1, customerId);
        	ResultSet rs = pstmt.executeQuery();
        	
        	CustomerBean customer;
        	if (!rs.next()) {
        		customer = null;
        	} else {
        		customer = new CustomerBean();
        		customer.setCustomerId(rs.getInt("customerId"));
        		customer.setUserName(rs.getString("userName"));
        		customer.setFirstName(rs.getString("firstName"));
        		customer.setLastName(rs.getString("lastName"));
        		customer.setDirectPassword(rs.getString("password"));
        		customer.setAddrLine1(rs.getString("addrLine1"));
        		customer.setAddrLine2(rs.getString("addrLine2"));
        		customer.setCity(rs.getString("city"));
        		customer.setState(rs.getString("state"));
        		customer.setZip(rs.getInt("zip"));
        		customer.setSalt(rs.getInt("salt"));
        		customer.setCash(rs.getDouble("cash")); // NEED TO BE CHANGED
        	}
        	
        	rs.close();
        	pstmt.close();
        	releaseConnection(con);
            return customer;
            
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