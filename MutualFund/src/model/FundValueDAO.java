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
	public FundValueDAO(String jdbcDriver, String jdbcURL, String tableName)
			throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);

	}

	public FundValueBean[] getFundValue(int customerId) throws MyDAOException {
		Connection con;
		try {
			con = getConnection();

			PreparedStatement pstmt = con
					.prepareStatement("SELECT position.shares, position.customerId, pricehistory.priceDate, pricehistory.price,"
							+ " FROM position"
							+ " INNER JOIN pricehistory"
							+ " ON position.fundId = pricehistory.fundId"
							+ " WHERE position.customerid=?"
							+ " ORDER BY priceDate DESC");
			pstmt.setInt(1, customerId);

			ResultSet rs = pstmt.executeQuery();

			List<FundValueBean> list = new ArrayList<FundValueBean>();
			while (rs.next()) {
				FundValueBean value = new FundValueBean();
				value.setFundId(rs.getInt("fundId"));
				value.setShares(rs.getString("shares"));
				list.add(value);
			}

			pstmt.close();
			releaseConnection(con);

			return list.toArray(new FundValueBean[list.size()]);

		} catch (SQLException e) {
			throw new MyDAOException(e);
		}

	}

	public FundValueBean[] getFundName(int customerId) throws MyDAOException {
		Connection con;
		try {
			con = getConnection();

			PreparedStatement pstmt = con
					.prepareStatement("SELECT position.customerId, fund.name,"
							+ " FROM position"
							+ " INNER JOIN fund"
							+ " ON position.fundId = fund.fundId"
							+ " WHERE position.customerid=?");
			pstmt.setInt(1, customerId);

			ResultSet rs = pstmt.executeQuery();

			List<FundValueBean> list = new ArrayList<FundValueBean>();
			while (rs.next()) {
				FundValueBean value = new FundValueBean();
				value.setFundName(rs.getString("name"));
				list.add(value);
			}

			pstmt.close();
			releaseConnection(con);

			return list.toArray(new FundValueBean[list.size()]);

		} catch (SQLException e) {
			throw new MyDAOException(e);
		}

	}
	
	public FundValueBean getLastTrading(int fundId) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();

			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ "fundPriceHistory" + " WHERE fundId=?"
					+ " ORDER BY priceDate DESC LIMIT 0,1");
			pstmt.setInt(1, fundId);
			ResultSet rs = pstmt.executeQuery();

			FundValueBean fundValueBean;
			if (!rs.next()) {
				fundValueBean = null;
			} else {
				fundValueBean = new FundValueBean();
				fundValueBean.setFundId(rs.getInt("fundId"));
				long priceL = rs.getLong("price");
				double price = priceL / 100.00;
				fundValueBean.setLastTradingPrice(price);
				fundValueBean.setLastTradingDate(rs.getDate("priceDate"));
			}

			rs.close();
			pstmt.close();
			releaseConnection(con);
			return fundValueBean;
		} catch (SQLException e) {
			throw new MyDAOException(e);
		}
	}

	public double getLastTradingPrice(int fundId) throws MyDAOException {
		FundValueBean value = getLastTrading(fundId);
		return value.getLastTradingPrice();
	}

	public java.util.Date getLastTradingDate(int fundId) throws MyDAOException {
		FundValueBean value = getLastTrading(fundId);
		return value.getLastTradingDate();
	}

	@Override
	protected void createTable() throws MyDAOException {
	}
}