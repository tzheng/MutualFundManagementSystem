package databean;

import java.util.Date;

public class FundGeneralInfoBean {
	private int fundId;
	private String name;
	private String symbol;
	private double lastTradingPriceInDouble;
	private String lastTradingPrice;
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
	public double getLastTradingPriceInDouble() {
		return lastTradingPriceInDouble;
	}
	public String getLastTradingPrice() {
		return lastTradingPrice;
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
	public void setLastTradingPriceInDouble(double lastTradingPriceInDouble) {
		this.lastTradingPriceInDouble = lastTradingPriceInDouble;
	}
	public void setLastTradingPrice(String lastTradingPrice) {
		this.lastTradingPrice = lastTradingPrice;
	}
	public void setLastTradingDate(Date lastTradingDate) {
		this.lastTradingDate = lastTradingDate;
	}
}
