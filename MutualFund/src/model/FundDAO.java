/**
 * @author Team Snipers (Team 1)
 * Jan 17, 2013
 */

package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FundDAO extends BaseDAO {
	
	public FundDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
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
