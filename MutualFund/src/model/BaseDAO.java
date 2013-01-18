package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.genericdao.ConnectionPool;

public class BaseDAO {
	private List<Connection> connectionPool = new ArrayList<Connection>();

	private ConnectionPool pool;
	private String tableName;
	
	public BaseDAO(ConnectionPool pool, String tableName) throws MyDAOException {
		this.pool = pool;
		this.tableName  = tableName;
		
		if (!tableExist()) createTable();
	}
	
	// Method to check if table exist
	private boolean tableExist() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
        	DatabaseMetaData metaData = con.getMetaData();
        	ResultSet rs = metaData.getTables(null, null, tableName, null);
        	
        	boolean answer = rs.next();
        	
        	rs.close();
        	releaseConnection(con);
        	
        	return answer;

        } catch (SQLException e) {
        	try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
    }
	
	// Method to Create New Table if Table Doesn't exist
	private void createTable() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE " + tableName 
					+ " (username VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, firstName VARCHAR(255), lastName VARCHAR(255), "
					+ "PRIMARY KEY(username))");
            stmt.close();
        	
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
    }
	
	// Concurrency control
	protected synchronized Connection getConnection() throws MyDAOException {
		if (connectionPool.size() > 0) {
			return connectionPool.remove(connectionPool.size()-1);
		}
		
        try {
        	//Load Driver
            Class.forName(pool.getDriverName());
        } catch (ClassNotFoundException e) {
            throw new MyDAOException(e);
        }

        try {
        	//Connect to Database
            return DriverManager.getConnection(pool.getURL());
        } catch (SQLException e) {
            throw new MyDAOException(e);
        }
	}
	
	protected synchronized void releaseConnection(Connection con) {
		connectionPool.add(con);
	}
}
