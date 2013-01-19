package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			
			PreparedStatement pstmt = con.prepareStatement("SELECT transaction.executedate," 
														+ " transaction.transactionType, fund.name, transaction.shares,"
														+ " transaction.shareprice, transaction.amount, transaction.transactionstatus"
														+ " FROM transaction"
														+ " LEFT JOIN fund" 
														+ " ON transaction.fundid = fund.fundid"
														+ " WHERE transaction.customerid=?"
														+ " ORDER BY executeDate DESC");
			pstmt.setInt(1, customerId);
			
			ResultSet rs = pstmt.executeQuery();
			
			List<TransactionHistoryBean> list = new ArrayList<TransactionHistoryBean>();
			while (rs.next()) {
				TransactionHistoryBean history = new TransactionHistoryBean();
				history.setTransactionDate(rs.getDate("executedate"));
				history.setOperation(rs.getString("transactionType"));
				history.setFundName(rs.getString("name"));
				history.setShareNumber(rs.getInt("shares"));
				history.setSharePrice(rs.getFloat("shareprice"));
				history.setDollarAmount(rs.getFloat("amount"));
				history.setTransactionStatus(rs.getString("transactionstatus"));
				
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
			
			PreparedStatement pstmt = con.prepareStatement("SELECT transaction.customerid, transaction.executedate," 
														+ " transaction.transactionType, fund.name, transaction.shares,"
														+ " transaction.shareprice, transaction.amount, transaction.transactionstatus"
														+ " FROM transaction"
														+ " LEFT JOIN fund" 
														+ " ON transaction.fundid = fund.fundid"
														+ " ORDER BY customerid, executedate DESC");
			
			ResultSet rs = pstmt.executeQuery();
			
			List<TransactionHistoryBean> list = new ArrayList<TransactionHistoryBean>();
			while (rs.next()) {
				TransactionHistoryBean history = new TransactionHistoryBean();
				history.setTransactionDate(rs.getDate("executedate"));
				history.setOperation(rs.getString("transactionType"));
				history.setFundName(rs.getString("name"));
				history.setShareNumber(rs.getInt("shares"));
				history.setSharePrice(rs.getFloat("shareprice"));
				history.setDollarAmount(rs.getFloat("amount"));
				history.setTransactionStatus(rs.getString("transactionstatus"));
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
