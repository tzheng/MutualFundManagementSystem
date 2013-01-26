/**
 * @author Team Snipers (Team 1)
 * Jan 20, 2013
 */

package databean;

import java.text.DecimalFormat;

public class PositionBean {
	private int customerId;
	private int fundId;
	private String fundName;
	private String fundSymbol;
	private double shares;
	private double availableShares;
	
	public int getCustomerId() {
		return customerId;
	}
	public int getFundId() {
		return fundId;
	}
	public String getFundName() {
		return fundName;
	}
	public String getFundSymbol() {
		return fundSymbol;
	}
	public double getShares() {
		return shares;
	}
	public double getAvailableShares() {
		return availableShares;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public void setFundSymbol(String fundSymbol) {
		this.fundSymbol = fundSymbol;
	}
	public void setShares(double shares) {
		this.shares = shares;
	}
	public void setAvailableShares(double availableShares) {
		this.availableShares = availableShares;
	}
	
}
