package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
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
			long priceL = Math.round(priceHistory.getPrice() * 100.00);
			pstmt.setLong(3, priceL); //CHANGE
			
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
			
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName +" WHERE fundId=?" + " ORDER BY priceDate DESC");
			pstmt.setInt(1, fund_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			List<FundPriceHistoryBean> list = new ArrayList<FundPriceHistoryBean>();
			while (rs.next()) {
				FundPriceHistoryBean price = new FundPriceHistoryBean();
				price.setFund_id(rs.getInt("fundId"));
				long priceL = rs.getLong("price");
				price.setPrice(priceL / 100.00);
				price.setPrice_date(rs.getDate("priceDate"));
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
	
	public FundGeneralInfoBean[] getAllFundsGeneralInfo() throws MyDAOException {
		Connection con = null;
		DecimalFormat formatter = new DecimalFormat("#0.00");
		
		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT d.fundId, d.name, d.symbol, c.priceDate, c.price" +
					" FROM fund d LEFT JOIN (" +
					" 	SELECT a.fundId, a.priceDate, a.price" +
					"   FROM pricehistory a INNER JOIN " +
					" 		(SELECT fundId, MAX(priceDate) AS lastDate from pricehistory GROUP BY fundId) b" +
					"	ON (a.fundId = b.fundId)" +
					" 	WHERE a.priceDate = b.lastDate) c" +
					" ON d.fundId = c.fundId");
			
			ResultSet rs = pstmt.executeQuery();
			
			List<FundGeneralInfoBean> list = new ArrayList<FundGeneralInfoBean>();
			while (rs.next()) {
				FundGeneralInfoBean bean = new FundGeneralInfoBean();
				bean.setFundId(rs.getInt("fundId"));
				bean.setName(rs.getString("name"));
				bean.setSymbol(rs.getString("symbol"));
				
				if (rs.getString("price") != null) {
					bean.setLastTradingDate(rs.getDate("priceDate"));
					
					long priceL = rs.getLong("price");
					double priceD = priceL / 100.00;
					bean.setLastTradingPrice(priceD);
					bean.setSpecifiedPrice(formatter.format(priceD));
				}
				
				list.add(bean);
			}
			
			pstmt.close();
			releaseConnection(con);
			
			return list.toArray(new FundGeneralInfoBean[list.size()]);
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
			
			PreparedStatement pstmt = con.prepareStatement("SELECT MAX(priceDate) AS priceDate FROM " + tableName);
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