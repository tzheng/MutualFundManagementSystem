package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import databean.FundPriceHistoryBean;
import databean.FundValueBean;
import databean.TransactionHistoryBean;

public class FundValueDAO extends BaseDAO {
	public FundValueDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	
	}
	public FundValueBean[] getFundValue(int customerId) throws MyDAOException {
		Connection con;
		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT position.shares, pricehistory.priceDate, pricehistory.price," 
														+ " FROM position"
														+ " INNER JOIN pricehistory" 
														+ " ON position.fundid = pricehistory.fundid"
														+ " WHERE position.customerid=?"
														+ " ORDER BY priceDate DESC");
			pstmt.setInt(1, customerId);
			
			ResultSet rs = pstmt.executeQuery();
			
			List<FundValueBean> list = new ArrayList<FundValueBean>();
			while (rs.next()) {
				FundValueBean value = new FundValueBean();
				value.setFundId(rs.getInt("fundid"));
				value.setShares(rs.getString("shares"));
				list.add(value);
			}
			
			pstmt.close();
			releaseConnection(con);
			
			return null;

} catch (SQLException  e) {
	throw new MyDAOException(e);
}
		
		}
	
	public FundPriceHistoryBean getLastTrading(int fundId) throws MyDAOException {
		Connection con = null;
		
		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " 
														+ tableName 
														+ " WHERE fundId=?"
														+ " ORDER BY priceDate DESC LIMIT 0,1");
			pstmt.setInt(1, fundId);
			ResultSet rs = pstmt.executeQuery();
			
			FundPriceHistoryBean fundPriceHistoryBean;
			if (!rs.next()) {
				fundPriceHistoryBean = null;
			} else {
				fundPriceHistoryBean = new FundPriceHistoryBean();
				fundPriceHistoryBean.setFund_id(rs.getInt("fundId"));
				long priceL = rs.getLong("price");
				double price = priceL / 100.00;
				fundPriceHistoryBean.setPrice(price);
				fundPriceHistoryBean.setPrice_date(rs.getDate("priceDate"));
			}
			
			rs.close();
			pstmt.close();
			releaseConnection(con);
			return fundPriceHistoryBean;
		} catch (SQLException e) {
			throw new MyDAOException(e);
		}
	}
	@Override
	protected void createTable() throws MyDAOException {
	}
}