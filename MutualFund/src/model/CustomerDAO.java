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



public class CustomerDAO extends BaseDAO {
	
	
	public CustomerDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	
	// Method to Create New Table if Table Doesn't exist
	protected void createTable() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE  TABLE " + tableName + 
            		" (customer_id INT NOT NULL AUTO_INCREMENT ," +
            		"user_name VARCHAR(255) NULL," +
            		"first_name VARCHAR(255) NULL ,"+
            		"last_name VARCHAR(255) NULL ,"+
            		"password VARCHAR(255) NULL ," +
            		"addr_line1 VARCHAR(255) NULL ," +
            		"addr_line2 VARCHAR(255) NULL ," +
            		"city VARCHAR(255) NULL ," +
            		"state VARCHAR(255) NULL ," +
            		"zip INT(11) NULL ," +
            		"cash DOUBLE(255,2) NULL ," +
            		"PRIMARY KEY (customer_id) );");
            stmt.close();
        	
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
    }
	
	public void create(CustomerBean customer) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName
												+ " (user_name, first_name, last_name, password, addr_line1, addr_line2, city, state, zip, cash) VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			
			pstmt.setString(1, customer.getUserName());
			pstmt.setString(2, customer.getFirstName());
			pstmt.setString(3, customer.getLastName());
			pstmt.setString(4, customer.getPassword());
			pstmt.setString(5, customer.getAddrLine1());
			pstmt.setString(6, customer.getAddrLine2());
			pstmt.setString(7, customer.getCity());
			pstmt.setString(8, customer.getState());
			pstmt.setInt(9, customer.getZip());
			
			
			int count = pstmt.executeUpdate();
			if (count != 1) throw new SQLException("Insert updated "+count+" rows");
			
			pstmt.close();
			releaseConnection(con);
			
		} catch (SQLException e) {
			throw new MyDAOException(e);
		}
	}
	
	public CustomerBean[] getUsers() throws Exception {
		Connection con = null;
        try {
        	con = getConnection();

        	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName);
        	ResultSet rs = pstmt.executeQuery();
        	
        	int count = 0;
        	while(rs.next()) {
        		++count;
        	}
        	
        	CustomerBean [] user = null;
        	if(count != 0) {
        		user = new CustomerBean[count];
        	}
        	if(rs.first()) {
        		for(int i=0;i<count;i++) {
            		user[i] = new CustomerBean();
            		user[i].setCustomerId(rs.getInt("customer_id"));
            		user[i].setUserName(rs.getString("user_name"));
            		user[i].setFirstName(rs.getString("first_name"));
            		user[i].setLastName(rs.getString("last_name"));
            		user[i].setPassword(rs.getString("password"));
            		user[i].setAddrLine1(rs.getString("addr_line1"));
            		user[i].setAddrLine2(rs.getString("addr_line2"));
            		user[i].setCity(rs.getString("city"));
            		user[i].setState(rs.getString("state"));
            		user[i].setZip(rs.getInt("zip"));
            		rs.next();
            	}
        	}
        	
        	rs.close();
        	pstmt.close();
        	releaseConnection(con);
            return user;
            
        } catch (Exception e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new Exception(e);
        }
	}
	
	public CustomerBean lookup(String emailAddress) throws Exception{
		Connection con = null;
        try {
        	con = getConnection();

        	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE email_address=?");
        	pstmt.setString(1,emailAddress);
        	ResultSet rs = pstmt.executeQuery();
        	
        	CustomerBean customer;
        	if (!rs.next()) {
        		customer = null;
        	} else {
        		customer = new CustomerBean();
        		customer.setCustomerId(rs.getInt("customer_id"));
        		customer.setUserName(rs.getString("user_name"));
        		customer.setFirstName(rs.getString("first_name"));
        		customer.setLastName(rs.getString("last_name"));
        		customer.setPassword(rs.getString("password"));
        		customer.setAddrLine1(rs.getString("addr_line1"));
        		customer.setAddrLine2(rs.getString("addr_line2"));
        		customer.setCity(rs.getString("city"));
        		customer.setState(rs.getString("state"));
        		customer.setZip(rs.getInt("zip"));
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
	
}

