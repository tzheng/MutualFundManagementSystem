package databean;

import java.util.Date;

public class FundValueBean {

	 private int fundId;
     private String fundName;
     private String shares;
     private Date lastTradingDate;
     private String lastTradingPrice;
     private String value;
	 private String firstName;
	 private String lastName;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
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
