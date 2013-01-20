package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PositionDAO extends BaseDAO {
	public PositionDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}

	@Override
	protected void createTable() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE " + tableName 
					+ " (customerId INT NOT NULL, fundId INT NOT NULL, share INT NOT NULL, "
					+ "PRIMARY KEY (customerId, fundId), FOREIGN KEY (customerId) REFERENCES customer (customerId), FOREIGN KEY (fundId) REFERENCES fund (fundId))");
            
            stmt.close();
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}

}
