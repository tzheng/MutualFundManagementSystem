package databean;

import java.util.Date;

public class FundGeneralInfoBean {
	private int fundId;
	private String fundName;
	private String symbol;
	private String lastTradingPrice;
	private Date lastTradingDate;
	public Date getLastTradingDate() {
		return lastTradingDate;
	}
	public void setLastTradingDate(Date lastTradingDate) {
		this.lastTradingDate = lastTradingDate;
	}
	public int getFundId() {
		return fundId;
	}
	public String getFundName() {
		return fundName;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getLastTradingPrice() {
		return lastTradingPrice;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setLastTradingPrice(String lastTradingPrice) {
		this.lastTradingPrice = lastTradingPrice;
	}
}
