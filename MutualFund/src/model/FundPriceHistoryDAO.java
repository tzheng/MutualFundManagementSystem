package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FundPriceHistoryDAO {
	private List<Connection> connectionPool = new ArrayList<Connection>();
	
	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	
	private void createTable() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE " + tableName 
					+ " (fund_id INT NOT NULL, price_date DATE NOT NULL, price FLOAT NOT NULL,"
					+ "PRIMARY KEY (fund_id, price_date), FOREIGN KEY (fund_id) REFERENCES fund (fund_id))");
            
            stmt.close();
        	
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
	}
	
	// Concurrency control
		private synchronized Connection getConnection() throws MyDAOException {
			if (connectionPool.size() > 0) {
				return connectionPool.remove(connectionPool.size()-1);
			}
			
	        try {
	        	//Load Driver
	            Class.forName(jdbcDriver);
	        } catch (ClassNotFoundException e) {
	            throw new MyDAOException(e);
	        }

	        try {
	        	//Connect to Database
	            return DriverManager.getConnection(jdbcURL);
	        } catch (SQLException e) {
	            throw new MyDAOException(e);
	        }
		}
		
		private synchronized void releaseConnection(Connection con) {
			connectionPool.add(con);
		}
}
