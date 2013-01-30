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
import java.util.ArrayList;
import java.util.List;

import databean.FundBean;

public class FundDAO extends BaseDAO {
	
	public FundDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	public synchronized void create(FundBean fund) throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
        	con.setAutoCommit(false);
        	
        	PreparedStatement pstmt = con.prepareStatement(
        			"INSERT INTO " + tableName + " (name, symbol) VALUES (?, ?)");
        	
        	pstmt.setString(1, fund.getName());
        	pstmt.setString(2, fund.getSymbol());
        	int count = pstmt.executeUpdate();
        	if (count != 1) throw new SQLException("Insert updated "+count+" rows");
        	
        	pstmt.close();
        	
        	con.commit();
            con.setAutoCommit(true);
        	releaseConnection(con);
        	
        } catch (Exception e) {
            try {
            	if (con != null) {
            		con.rollback();
            		con.close();
            	}
            } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
	
	protected void createTable() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            
            stmt.executeUpdate(
            		"CREATE TABLE " + tableName +
            		" (fundId INT NOT NULL AUTO_INCREMENT," +
            		" name VARCHAR(255) NOT NULL UNIQUE," +
            		" symbol VARCHAR(255) NOT NULL," +
            		" PRIMARY KEY(fundId))");
            
            stmt.close();
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
    }
	
	public FundBean read(String fundName) throws MyDAOException{
		Connection con = null;
        try {
        	con = getConnection();

        	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE name=?");
        	pstmt.setString(1, fundName);
        	ResultSet rs = pstmt.executeQuery();
        	
        	FundBean fund;
        	if (!rs.next()) {
        		fund = null;
        	} else {
        		fund = new FundBean();
        		fund.setFundId(rs.getInt("fundId"));
        		fund.setName(rs.getString("name"));
        		fund.setSymbol(rs.getString("symbol"));
        	}
        	
        	rs.close();
        	pstmt.close();
        	releaseConnection(con);
            return fund;
            
        } catch (Exception e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
	public FundBean readSymbol(String symbol) throws MyDAOException{
		Connection con = null;
        try {
        	con = getConnection();

        	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE symbol=?");
        	pstmt.setString(1, symbol);
        	ResultSet rs = pstmt.executeQuery();
        	
        	FundBean fund;
        	if (!rs.next()) {
        		fund = null;
        	} else {
        		fund = new FundBean();
        		fund.setFundId(rs.getInt("fundId"));
        		fund.setName(rs.getString("name"));
        		fund.setSymbol(rs.getString("symbol"));
        	}
        	
        	rs.close();
        	pstmt.close();
        	releaseConnection(con);
            return fund;
            
        } catch (Exception e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
	
	public FundBean[] readAllFunds() throws MyDAOException {
		Connection con;
		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT fundId,name,symbol FROM " + tableName);
			ResultSet rs = pstmt.executeQuery();
			
			List<FundBean> list = new ArrayList<FundBean>();
			while (rs.next()) {
				FundBean fund = new FundBean();
				fund.setFundId(rs.getInt("fundId"));
				fund.setName(rs.getString("name"));
				fund.setSymbol(rs.getString("symbol"));
				list.add(fund);
			}
			
			pstmt.close();
			releaseConnection(con);
			
			return list.toArray(new FundBean[list.size()]);
		} catch (SQLException e) {
			throw new MyDAOException(e);
		}
	}
}
