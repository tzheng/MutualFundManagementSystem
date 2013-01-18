/**
 * @author Team Snipers (Team 1)
 * Jan 17, 2013
 */

package model;

import java.sql.*;
import java.util.*;

import databean.EmployeeBean;

public class EmployeeDAO {
	
	private List<Connection> connectionPool = new ArrayList<Connection>();

	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	
	public EmployeeDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		this.jdbcDriver = jdbcDriver;
		this.jdbcURL    = jdbcURL;
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
	
	
	//Method to create Employee
	public void create(EmployeeBean employee) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName
												+ " (username, password, firstName, lastName) VALUES (?,?,?,?)");
			
			
			pstmt.setString(1, employee.getUserName());
			pstmt.setString(2, employee.getPassword());
			pstmt.setString(3, employee.getFirstName());
			pstmt.setString(4, employee.getLastName());
			
			int count = pstmt.executeUpdate();
			if (count != 1) throw new SQLException("Insert updated "+count+" rows");
			
			pstmt.close();
			releaseConnection(con);
			
		} catch (SQLException e) {
			throw new MyDAOException(e);
		}
	}
	
	// Method to Read employee info from the database
	public EmployeeBean lookup(String username) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE userName=?");
			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery();
			
			EmployeeBean employee;
			if (!rs.next()) {
				employee = null;
			} else {
				employee = new EmployeeBean();
				employee.setUserName(rs.getString("userName"));
				employee.setPassword(rs.getString("password"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setLastName(rs.getString("lastName"));
			}
			
			rs.close();
			pstmt.close();
			releaseConnection(con);
			
			return employee;
		} catch (SQLException e) {
			throw new MyDAOException(e);
		}
	}
}
