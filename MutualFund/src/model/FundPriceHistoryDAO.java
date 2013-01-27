package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import databean.FundGeneralInfoBean;
import databean.FundPriceHistoryBean;

public class FundPriceHistoryDAO extends BaseDAO {
	
	public FundPriceHistoryDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName); 
		this.tableName = tableName;
	}
	
	protected void createTable() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE " + tableName 
					+ " (fundId INT NOT NULL, priceDate DATE NOT NULL, price BIGINT(32) NOT NULL, "
					+ "PRIMARY KEY (fundId, pricedate), FOREIGN KEY (fundId) REFERENCES fund (fundId))");
            
            stmt.close();
        	releaseConnection(con);

        } catch (SQLException e) {
            try {
            	if (con != null) {
            		con.rollback();
            		con.close();
            	}
            } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
		}
	}
	 
	public void create(FundPriceHistoryBean priceHistory) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName 
														+ " (fundId, pricedate, price) VALUES (?,?,?)");
			
			pstmt.setInt(1, priceHistory.getFund_id());
			// convert java.utl.date to java.sql.date so that can insert date to ...
			Date sqlDate = new Date(priceHistory.getPrice_date().getTime());
			pstmt.setDate(2, sqlDate);
			pstmt.setDouble(3, priceHistory.getPrice()); //CHANGE
			
			int count = pstmt.executeUpdate();
			if (count != 1) throw new SQLException("insert updated " + count + "rows");
			
			pstmt.close();
			releaseConnection(con);
			
		} catch (SQLException e) {
            try {
            	if (con != null) {
            		con.rollback();
            		con.close();
            	}
            } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
		}
	}
	
	public FundPriceHistoryBean[] getFundPriceHistory(int fund_id) throws MyDAOException {
		Connection con = null;
		
		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName +" WHERE fundId=?");
			pstmt.setInt(1, fund_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			List<FundPriceHistoryBean> list = new ArrayList<FundPriceHistoryBean>();
			while (rs.next()) {
				FundPriceHistoryBean price = new FundPriceHistoryBean();
				price.setFund_id(rs.getInt("fundid"));
				price.setPrice(rs.getDouble("price")); //change
				price.setPrice_date(rs.getDate("pricedate"));
				list.add(price);
			}
			
			pstmt.close();
			releaseConnection(con);
			
			return list.toArray(new FundPriceHistoryBean[list.size()]);
		} catch (SQLException e) {
            try {
            	if (con != null) {
            		con.rollback();
            		con.close();
            	}
            } catch (SQLException e2) { /* ignore */ }
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
            try {
            	if (con != null) {
            		con.rollback();
            		con.close();
            	}
            } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
		}
	}
	
	public Date getLastTradingDateOfALLFunds() throws MyDAOException {
		Connection con = null;
		
		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT MAX(priceDate) FROM " + tableName);
			ResultSet rs = pstmt.executeQuery();
			
			Date date;
			if (!rs.next()) {
				date = null;
			} else {
				date = rs.getDate("priceDate");
			}
			
			rs.close();
			pstmt.close();
			releaseConnection(con);
			return date;
		} catch (SQLException e) {
            try {
            	if (con != null) {
            		con.rollback();
            		con.close();
            	}
            } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
		}
	}
	
	public double getLastTradingPrice(int fundId) throws MyDAOException {
		FundPriceHistoryBean fund = getLastTrading(fundId);
		return fund.getPrice();
	}
	
	public java.util.Date getLastTradingDate(int fundId) throws MyDAOException {
		FundPriceHistoryBean fund = getLastTrading(fundId);
		return fund.getPrice_date();
	}
}