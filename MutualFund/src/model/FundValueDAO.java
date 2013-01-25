package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import databean.TransactionHistoryBean;

public class FundValueDAO extends BaseDAO {
	public FundValueDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	
	}
	public FundValueBean[] getFundValue(int customerId) throws MyDAOException {
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
			
}
}
}