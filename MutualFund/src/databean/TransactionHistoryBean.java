package databean;

import java.util.Date;

public class TransactionHistoryBean {
	private Date transactionDate;
	private String operation;  //in database, it is transactionType
	private String fundName; // in database, it is fund.name
	private int shareNumber; // in database, it is shares
	private float sharePrice;
	private float dollarAmount;  // in dataprice, it is amount
	private String transactionStatus;
	public Date getTransactionDate() {
		return transactionDate;
	}
	public String getOperation() {
		return operation;
	}
	public String getFundName() {
		return fundName;
	}
	public int getShareNumber() {
		return shareNumber;
	}
	public float getSharePrice() {
		return sharePrice;
	}
	public float getDollarAmount() {
		return dollarAmount;
	}
	public String getTransactionStatus() {
		return transactionStatus;
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
	public void setShareNumber(int shareNumber) {
		this.shareNumber = shareNumber;
	}
	public void setSharePrice(float sharePrice) {
		this.sharePrice = sharePrice;
	}
	public void setDollarAmount(float dollarAmount) {
		this.dollarAmount = dollarAmount;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
}
