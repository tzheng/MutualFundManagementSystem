/**
 * @author Team Snipers (Team 1)
 * Jan 20, 2013
 */

package databean;

public class PositionBean {
	private int customerId;
	private int fundId;
	private int shares;
	
	public int getCustomerId() {
		return customerId;
	}
	public int getFundId() {
		return fundId;
	}
	public int getShares() {
		return shares;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public void setShares(int shares) {
		this.shares = shares;
	}
	
	
}
