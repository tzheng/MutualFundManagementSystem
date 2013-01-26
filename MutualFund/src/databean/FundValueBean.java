package databean;

import java.sql.Date;

public class FundValueBean {
     private int fundId;
     private String fundName;
     private String shares;
     private Date lastTradingDate;
     private double lastTradingPrice;
        
	
	
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
	public String getShares() {
		return shares;
	}
	public void setShares(String shares) {
		this.shares = shares;
	}
	public Date getLastTradingDate() {
		return lastTradingDate;
	}
	public void setLastTradingDate(Date lastTradingDate) {
		this.lastTradingDate = lastTradingDate;
	}
	
     
}
