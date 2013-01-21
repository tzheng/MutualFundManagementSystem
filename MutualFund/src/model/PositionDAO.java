package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databean.PositionBean;

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
        			"SELECT * FROM " + tableName + " WHERE customerId = ?");
        	pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();
            
            List<PositionBean> list = new ArrayList<PositionBean>();
            while (rs.next()) {
            	PositionBean bean = new PositionBean();
            	bean.setCustomerId(rs.getInt("customerId"));
            	bean.setFundId(rs.getInt("fundId"));
            	bean.setShares(rs.getInt("shares"));
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

	@Override
	protected void createTable() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE " + tableName 
					+ " (customerId INT NOT NULL, fundId INT NOT NULL, shares INT NOT NULL, "
					+ "PRIMARY KEY (customerId, fundId), FOREIGN KEY (customerId) REFERENCES customer (customerId), FOREIGN KEY (fundId) REFERENCES fund (fundId))");
            
            stmt.close();
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
}
