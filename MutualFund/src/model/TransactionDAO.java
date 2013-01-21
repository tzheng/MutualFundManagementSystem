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
        	
        	Date date;
        	if (!rs.next()) {
        		date = null;
        	} else {
        		date = rs.getDate("executeDate");
        	}
        	
        	rs.close();
        	pstmt.close();
        	releaseConnection(con);
            return date;
            
        } catch (Exception e) {
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
														+ " (executeDate, transactionId, customerId, transactionType, transactionStatus, amount) "
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
					+ " (transactionId INT NOT NULL AUTO_INCREMENT,customerId INT NOT NULL AUTO_INCREMENT,fundId INT NOT NULL AUTO_INCREMENT"
					+ " executeDate DATE NOT NULL, shares INT, sharePrice FLOAT,transactionType VARCHAR(20),transactionStatus VARCHAR(10) NOT NULL,amount FLOAT,"
					+ " PRIMARY KEY(transactionId), FOREIGN KEY (customerId) REFERENCES Position(customerId),Customer(customerId), FOREIGN KEY(fundId) REFERENCES Position(fundId))");
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
