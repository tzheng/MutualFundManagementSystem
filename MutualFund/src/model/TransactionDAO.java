package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.MyDAOException;


public class TransactionDAO extends BaseDAO{

	private List<Connection> connectionPool = new ArrayList<Connection>();

	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	
	public TransactionDAO(String jdbcDriver, String jdbcURL, String tableName)
			throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}

	
	protected void createTable() throws MyDAOException {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE "
					+ tableName
					+ " (transactionId INT NOT NULL AUTO_INCREMENT,customerId INT NOT NULL AUTO_INCREMENT,fundId INT NOT NULL AUTO_INCREMENT"
					+ " execute_date DATE NOT NULL, shares INT, sharePrice FLOAT,transactionType VARCHAR(20),transactionStatus VARCHAR(10) NOT NULL,amount FLOAT,"
					+ " PRIMARY KEY(transactionId), FOREIGN KEY (customerId) REFERENCES Position(customerId),Customer(customerId), FOREIGN KEY(fundId) REFERENCES Position(fundId))");
			stmt.close();
			releaseConnection(con);
		} catch (SQLException e) {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e2) { /* ignore */
			}
			throw new MyDAOException(e);
		}
	}
}
