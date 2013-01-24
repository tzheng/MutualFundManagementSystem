package databean;

import java.util.Date;

public class TransactionHistoryBean {
	private Date transactionDate;
	private String operation;  //in database, it is transactionType
	private String fundName; // in database, it is fund.name
	//private double shareNumber; // in database, it is shares
	private String shareNumber;
	//private double sharePrice;
	private String sharePrice;
	
	//private double dollarAmount;  // in dataprice, it is amount
	private String dollarAmount;
	private String transactionStatus;
	private int customerId;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String username;
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	public String getOperation() {
		return operation;
	}
	public String getFundName() {
		return fundName;
	}
	public String getShareNumber() {
		return shareNumber;
	}
	public String getSharePrice() {
		return sharePrice;
	}
	public String getDollarAmount() {
		return dollarAmount;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public void setShareNumber(String shareNumber) {
		this.shareNumber = shareNumber;
	}
	public void setSharePrice(String sharePrice) {
		this.sharePrice = sharePrice;
	}
	public void setDollarAmount(String dollarAmount) {
		this.dollarAmount = dollarAmount;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
}
