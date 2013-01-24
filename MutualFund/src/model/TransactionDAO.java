package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.MyDAOException;

public class TransactionDAO extends BaseDAO{
	public TransactionDAO(String jdbcDriver, String jdbcURL, String tableName)
			throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	public Date getCustomerLastTradeDate(int customerId) throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();

        	PreparedStatement pstmt = con.prepareStatement(
        			"SELECT * FROM " + tableName + 
        			" WHERE customerId = ?" +
        			" ORDER BY executeDate DESC" +
        			" LIMIT 1");
        	pstmt.setInt(1, customerId);
        	ResultSet rs = pstmt.executeQuery();
        	
        	Date lastTradeDate;
        	if (!rs.next()) {
        		lastTradeDate = null;
        	} else {
        		lastTradeDate = rs.getDate("executeDate");
        	}
        	
        	rs.close();
        	pstmt.close();
        	releaseConnection(con);
            return lastTradeDate;
            
        } catch (Exception e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
	
	public void buyFund(int customerId, int fundId, double amount) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
        	con.setAutoCommit(false);
        	
			// you might need to change this query, i didn't finish it.
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName  
														+ " (customerId, fundId, transactionType, transactionStatus, amount) "
														+ " VALUES (?,?,?,?,?)");
			// add the vaule to prepare statement.
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, fundId);
			pstmt.setInt(3, 1);
			pstmt.setInt(4, 0);
			long amountL = Math.round(amount * 100.00);
			pstmt.setLong(5, amountL);
			
			int count = pstmt.executeUpdate();
        	if (count != 1) throw new SQLException("Insert updated "+count+" rows");
			
        	pstmt.close();
        	
        	con.commit();
            con.setAutoCommit(true);
        	releaseConnection(con);
			
		} catch (SQLException e) {
			try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
		}
	}
	
	public void insertCash(double cash) throws MyDAOException {
		//check, deposit
		Connection con;
		try {
			con = getConnection();
			// you might need to change this query, i didn't finish it.
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName  
														+ " (customerId, transactionType, transactionStatus, amount) "
														+ " VALUES (?,?,?,?,?,?)");
			// add the vaule to prepare statement.
		} catch (SQLException e) {
			
		}
			
	}
	
	public void insertFund() {
		//buy fund and sell fund
	}
	
	protected void createTable() throws MyDAOException {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE "
					+ tableName
					+ " (transactionId INT NOT NULL AUTO_INCREMENT, customerId INT NOT NULL, fundId INT, "
					+ " executeDate DATE, shares BIGINT(32), sharePrice BIGINT(32),transactionType INT(1) NOT NULL,transactionStatus INT(1) NOT NULL,amount BIGINT(32),"
					+ " PRIMARY KEY(transactionId), FOREIGN KEY (customerId) REFERENCES Customer (customerId))");
			stmt.close();
			releaseConnection(con);
		} catch (SQLException e) {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e2) { /* ignore */
			}
			throw new MyDAOException(e);
		}
	}
}
