package databean;

import java.sql.Date;

public class FundValueBean {
     private int fundId;
     private String name;
     private String shares;
     private Date lastTradingDate;
     private String lastTradingPrice;
        
	
	public String getLastTradingPrice() {
		return lastTradingPrice;
	}
	public void setLastTradingPrice(String lastTradingPrice) {
		this.lastTradingPrice = lastTradingPrice;
	}
	
	public int getFundId() {
		return fundId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
