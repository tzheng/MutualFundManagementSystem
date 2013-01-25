package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import databean.TransactionHistoryBean;

public class TransactionHistoryDAO extends BaseDAO {
	public TransactionHistoryDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	public void createTable() {
		
	}
	
	//get transactions of specified user
	public TransactionHistoryBean[] getTransactions(int customerId) throws MyDAOException {
		Connection con;
		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT transaction.transactionId, transaction.customerid, transaction.executedate," 
														+ " transaction.transactionType, fund.name, transaction.shares,"
														+ " transaction.shareprice, transaction.amount, transaction.transactionstatus"
														+ " FROM transaction"
														+ " LEFT JOIN fund" 
														+ " ON transaction.fundid = fund.fundid"
														+ " WHERE transaction.customerid=?"
														+ " ORDER BY transactionId DESC");
			pstmt.setInt(1, customerId);
			
			ResultSet rs = pstmt.executeQuery();
			
			List<TransactionHistoryBean> list = new ArrayList<TransactionHistoryBean>();
			while (rs.next()) {
				TransactionHistoryBean history = new TransactionHistoryBean();
				history.setTransactionDate(rs.getDate("executedate"));
				//convert int type to real operation: 
				// 1 = BUY FUND, 2 = SELL FUND,  3 = REQUEST CHECK  4 = DEPOSIT CHECK 
				int type = rs.getInt("transactionType");
				String typeStr = "";
				switch (type) {
				case 1:
					typeStr = "BUY FUND";
					break;
				case 2:
					typeStr = "SELL FUND";
					break;
				case 3:
					typeStr = "REQUEST CHECK";
					break;
				case 4:
					typeStr = "DEPOSIT CHECK";
					break;
				}
				history.setOperation(typeStr);
				
				history.setFundName(rs.getString("name"));
				
				
				DecimalFormat formatter = new DecimalFormat("#0.000");
				//convert sharenumber from LONG to DOUBLE
				long shareNumber = rs.getLong("shares");
				double shares = shareNumber / 1000.000;
				//BigDecimal share1 = new BigDecimal(shares);
				history.setShareNumber(formatter.format(shares));
				
				formatter = new DecimalFormat("#0.00");
				long sharePrice = rs.getLong("shareprice");
				double price = sharePrice / 100.00;
				history.setSharePrice(formatter.format(price));
				
				long amount = rs.getLong("amount");
				double amountD = amount / 100.00;
				history.setDollarAmount(formatter.format(amountD));
				
				int status = rs.getInt("transactionstatus");
				String statusStr = "";
				switch (status) {
				case 1:
					statusStr = "Processed";
					break;
				case 0:
					statusStr = "Pending";
					break;
				case -1:
					statusStr = "Rejected";
					break;
				}
				history.setTransactionStatus(statusStr);
				history.setCustomerId(rs.getInt("customerid"));
				list.add(history);
			}
			
			pstmt.close();
			releaseConnection(con);
			
			return list.toArray(new TransactionHistoryBean[list.size()]);
			
		} catch (SQLException  e) {
			throw new MyDAOException(e);
		}
	}
	
	// get all transactions
	public TransactionHistoryBean[] getTransactions() throws MyDAOException {
		Connection con;
		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT transaction.transactionId, transaction.customerid, transaction.executedate," 
														+ " transaction.transactionType, fund.name, transaction.shares,"
														+ " transaction.shareprice, transaction.amount, transaction.transactionstatus"
														+ " FROM transaction"
														+ " LEFT JOIN fund" 
														+ " ON transaction.fundid = fund.fundid"
														+ " ORDER BY customerid, transactionId DESC");
			
			ResultSet rs = pstmt.executeQuery();
			
			List<TransactionHistoryBean> list = new ArrayList<TransactionHistoryBean>();
			while (rs.next()) {
				TransactionHistoryBean history = new TransactionHistoryBean();
				if (rs.getDate("executedate") != null)
					history.setTransactionDate(rs.getDate("executedate"));
				//convert int type to real operation: 
				// 1 = BUY FUND, 2 = SELL FUND,  3 = REQUEST CHECK  4 = DEPOSIT CHECK 
				int type = rs.getInt("transactionType");
				String typeStr = "";
				switch (type) {
				case 1:
					typeStr = "BUY FUND";
					break;
				case 2:
					typeStr = "SELL FUND";
					break;
				case 3:
					typeStr = "REQUEST CHECK";
					break;
				case 4:
					typeStr = "DEPOSIT CHECK";
					break;
				}
				history.setOperation(typeStr);
				
				history.setFundName(rs.getString("name"));
				
				
				DecimalFormat formatter = new DecimalFormat("#0.000");
				//convert sharenumber from LONG to DOUBLE
				long shareNumber = rs.getLong("shares");
				double shares = shareNumber / 1000.000;
				//BigDecimal share1 = new BigDecimal(shares);
				history.setShareNumber(formatter.format(shares));
				
				formatter = new DecimalFormat("#0.00");
				long sharePrice = rs.getLong("shareprice");
				double price = sharePrice / 100.00;
				history.setSharePrice(formatter.format(price));
				
				long amount = rs.getLong("amount");
				double amountD = amount / 100.00;
				history.setDollarAmount(formatter.format(amountD));
				
				int status = rs.getInt("transactionstatus");
				String statusStr = "";
				switch (status) {
				case 1:
					statusStr = "Processed";
					break;
				case 0:
					statusStr = "Pending";
					break;
				case -1:
					statusStr = "Rejected";
					break;
				}
				history.setTransactionStatus(statusStr);
				history.setCustomerId(rs.getInt("customerid"));
				list.add(history);
			}
			
			pstmt.close();
			releaseConnection(con);
			
			return list.toArray(new TransactionHistoryBean[list.size()]);
			
		} catch (SQLException  e) {
			throw new MyDAOException(e);
		}
	}
}
