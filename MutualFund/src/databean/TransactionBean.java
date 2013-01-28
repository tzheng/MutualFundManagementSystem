package databean;

import java.util.Date;

public class TransactionBean {
	private int transactionId;
	private int customerId;
	private int fundId;
	private Date executeDate;
	private double shares;
	private double sharePrice;
	private String transactionType;
	private String transactionStatus;
	private double amount;
	
	public int getTransactionId() {
		return transactionId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public int getFundId() {
		return fundId;
	}
	public Date getExecuteDate() {
		return executeDate;
	}
	public double getShares() {
		return shares;
	}
	public double getSharePrice() {
		return sharePrice;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public double getAmount() {
		return amount;
	}
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}
	public void setShares(double shares) {
		this.shares = shares;
	}
	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}



