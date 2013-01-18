package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import databean.FundPriceHistoryBean;




public class FundPriceHistoryDAO extends BaseDAO {
	
	public FundPriceHistoryDAO(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
		
	}
	
	public void createTable() throws MyDAOException {
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
	 
	public void create(FundPriceHistoryBean priceHistory) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName 
														+ " (fund_id, price_date, price) VALUES (?,?,?)");
			
			pstmt.setInt(1, priceHistory.getFund_id());
			// convert java.utl.date to java.sql.date so that can insert date to ...
			Date sqlDate = new Date(priceHistory.getPrice_date().getTime());
			pstmt.setDate(2, sqlDate);
			pstmt.setDouble(3, priceHistory.getPrice());
			
			int count = pstmt.executeUpdate();
			if (count != 1) throw new SQLException("insert updated " + count + "rows");
			
			pstmt.close();
			releaseConnection(con);
			
		} catch (SQLException e) {
			throw new MyDAOException(e);
		} 
	}

}




