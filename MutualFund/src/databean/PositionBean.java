/**
 * @author Team Snipers (Team 1)
 * Jan 20, 2013
 */

package databean;

public class PositionBean {
	private int customerId;
	private int fundId;
	private double shares;
	private double availableShares;
	public int getCustomerId() {
		return customerId;
	}
	public int getFundId() {
		return fundId;
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
	public void setShares(double shares) {
		this.shares = shares;
	}
	public void setAvailableShares(double availableShares) {
		this.availableShares = availableShares;
	}
	
}
