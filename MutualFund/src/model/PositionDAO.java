package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databean.PositionBean;
import databean.UserBean;

public class PositionDAO extends BaseDAO {
	public PositionDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	public PositionBean[] getCustomerPortfolio(int customerId) throws MyDAOException {
		Connection con = null;
    	try {
        	con = getConnection();
        	con.setAutoCommit(false);
        	
        	PreparedStatement pstmt = con.prepareStatement(
        			"SELECT * FROM " + tableName + " WHERE customerId = ? ORDER BY fundId ASC");
        	pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();
            
            List<PositionBean> list = new ArrayList<PositionBean>();
            while (rs.next()) {
            	PositionBean bean = new PositionBean();
            	bean.setCustomerId(rs.getInt("customerId"));
            	bean.setFundId(rs.getInt("fundId"));
            	bean.setShares(rs.getLong("shares") / 1000.00);
            	bean.setAvailableShares(rs.getLong("availableShares") / 1000.00);
            	list.add(bean);
            }
            rs.close();
            pstmt.close();
            
            con.commit();
            con.setAutoCommit(true);
            releaseConnection(con);
            
            return list.toArray(new PositionBean[list.size()]);
            
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
	
	public void update(PositionBean bean) {
		Connection con = null;
        try {
        	con = getConnection();
        	con.setAutoCommit(false);
        	
        	PreparedStatement pstmt = con.prepareStatement(
        			"UPDATE " + tableName + " SET availableShares = ? WHERE customerId = ? and fundId = ?");
        	
        	long sharesLong = Math.round(bean.getAvailableShares() * 1000.00);
        	pstmt.setLong(1, sharesLong);
        	pstmt.setInt(2, bean.getCustomerId());
        	pstmt.setInt(3, bean.getFundId());
        	int count = pstmt.executeUpdate();
        	if (count != 1) throw new SQLException("Updated "+count+" rows");
        	
        	pstmt.close();
        	
        	con.commit();
            con.setAutoCommit(true);
        	releaseConnection(con);
        	
        } catch (SQLException e) {
    		// If this exception is caused by database-related problem, roll back and close this connection.
    		try {
            	if (con != null) {
            		con.rollback();
            		con.close();
            	}
            } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
		} finally {
			// If this exception is caused by illegal update action, roll back but don't close this connection.
			try {
            	if (con != null) {
            		con.rollback();
            		con.setAutoCommit(true);
            		releaseConnection(con);
            	}
            } catch (SQLException e2) { /* ignore */ }
		}
	}

	@Override
	protected void createTable() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE " + tableName 
					+ " (customerId INT NOT NULL, fundId INT NOT NULL, shares BIGINT(32) NOT NULL, availableShares BIGINT(32) NOT NULL,"
					+ " PRIMARY KEY (customerId, fundId), FOREIGN KEY (customerId) REFERENCES customer (customerId), FOREIGN KEY (fundId) REFERENCES fund (fundId))");
            
            stmt.close();
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
}
