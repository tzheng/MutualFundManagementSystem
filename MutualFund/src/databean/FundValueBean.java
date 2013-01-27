package databean;

import java.util.Date;

public class FundValueBean {
//	 private String firstName;
//	 private String lastName;
//	 private String addrLine1;
//	 private String addrLine2;
//	 private double cash;
	 
//     public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getAddrLine1() {
//		return addrLine1;
//	}
//	public void setAddrLine1(String addrLine1) {
//		this.addrLine1 = addrLine1;
//	}
//	public String getAddrLine2() {
//		return addrLine2;
//	}
//	public void setAddrLine2(String addrLine2) {
//		this.addrLine2 = addrLine2;
//	}
//	public double getCash() {
//		return cash;
//	}
//	public void setCash(double cash) {
//		this.cash = cash;
//	}
	private int fundId;
     private String fundName;
     private double shares;
     private Date lastTradingDate;
     private String lastTradingPrice;
     private String value;
	
	
	
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
