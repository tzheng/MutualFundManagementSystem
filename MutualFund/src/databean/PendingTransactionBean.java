/**
 * @author Team Snipers (Team 1)
 * Jan 27, 2013
 */

package databean;

import java.util.Date;

public class PendingTransactionBean {
	private int transactionId;
	private int customerId;
	private int fundId;
	private Date executeDate;
	private double toSellShares;
	private double ownedShares;
	private double price;
	private int transactionType;
	private double amount;
	private double cash;
	
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
	public double getToSellShares() {
		return toSellShares;
	}
	public double getOwnedShares() {
		return ownedShares;
	}
	public double getPrice() {
		return price;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public double getCash() {
		return cash;
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
	public void setToSellShares(double toSellShares) {
		this.toSellShares = toSellShares;
	}
	public void setOwnedShares(double ownedShares) {
		this.ownedShares = ownedShares;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
}
