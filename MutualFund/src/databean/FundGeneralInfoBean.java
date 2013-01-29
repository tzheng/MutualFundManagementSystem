package databean;

import java.util.Date;

public class FundGeneralInfoBean {
	private int fundId;
	private String name;
	private String symbol;
	private double lastTradingPrice;
	private String specifiedPrice;
	private Date lastTradingDate;
	
	public int getFundId() {
		return fundId;
	}
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	public double getLastTradingPrice() {
		return lastTradingPrice;
	}
	public String getSpecifiedPrice() {
		return specifiedPrice;
	}
	public Date getLastTradingDate() {
		return lastTradingDate;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setLastTradingPrice(double lastTradingPrice) {
		this.lastTradingPrice = lastTradingPrice;
	}
	public void setSpecifiedPrice(String specifiedPrice) {
		this.specifiedPrice = specifiedPrice;
	}
	public void setLastTradingDate(Date lastTradingDate) {
		this.lastTradingDate = lastTradingDate;
	}
}
