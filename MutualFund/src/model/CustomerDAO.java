/**
 * @author Team Snipers (Team 1)
 * Jan 17, 2013
 */

package model;

import java.sql.*;
import java.util.*;


public class CustomerDAO extends BaseDAO {
	
	
	public CustomerDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	
	// Method to Create New Table if Table Doesn't exist
	protected void createTable() throws MyDAOException {
		Connection con = null;
        try {
        	con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE  TABLE " + tableName + 
            		" (customer_id INT NOT NULL AUTO_INCREMENT ," +
            		"user_name VARCHAR(255) NULL," +
            		"first_name VARCHAR(255) NULL ,"+
            		"last_name VARCHAR(255) NULL ,"+
            		"password VARCHAR(255) NULL ," +
            		"addr_line1 VARCHAR(255) NULL ," +
            		"addr_line2 VARCHAR(255) NULL ," +
            		"city VARCHAR(255) NULL ," +
            		"state VARCHAR(255) NULL ," +
            		"zip INT(11) NULL ," +
            		"cash DOUBLE(255,2) NULL ," +
            		"PRIMARY KEY (customer_id) );");
            stmt.close();
        	
        	releaseConnection(con);

        } catch (SQLException e) {
            try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
        	throw new MyDAOException(e);
        }
    }
	
	
}

