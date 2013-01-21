/**
 * @author Team Snipers (Team 1)
 * Jan 17, 2013
 */

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import databean.FundBean;

public class FundDAO extends BaseDAO {
	
	public FundDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	public void create(FundBean fund) throws MyDAOException {
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
}
