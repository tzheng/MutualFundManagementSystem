package databean;

import java.util.Date;

public class FundValueBean {
     private int fundId;
     private String fundName;
     private double shares;
     private Date lastTradingDate;
     private double lastTradingPrice;
     private double value;
	
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getLastTradingPrice() {
		return lastTradingPrice;
	}
	public void setLastTradingPrice(double lastTradingPrice) {
		this.lastTradingPrice = lastTradingPrice;
	}
	public int getFundId() {
		return fundId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String name) {
		this.fundName = name;
	}

	public double getShares() {
		return shares;
	}
	public void setShares(double shares) {
		this.shares = shares;
	}
	public Date getLastTradingDate() {
		return lastTradingDate;
	}
	public void setLastTradingDate(Date lastTradingDate) {
		this.lastTradingDate = lastTradingDate;
	}
	
     
}
