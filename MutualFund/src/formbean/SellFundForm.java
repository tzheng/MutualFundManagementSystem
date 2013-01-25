/**
 * @author Team Snipers (Team 1)
 * Jan 24, 2013
 */

package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFundForm extends FormBean {
	private String fundId;
	private String shares;
	
	public String getFundId() {
		return fundId;
	}
	public String getShares() {
		return shares;
	}
	
	public void setFundId(String fundId) {
		this.fundId = trimAndConvert(fundId, "<>\"");
	}
	public void setShares(String shares) {
		this.shares = trimAndConvert(shares, "<>\"");
	}
	
	public int getFundIdAsInt() {
		try {
			return Integer.parseInt(fundId);
		} catch (NumberFormatException e) {
			// call getValidationErrors() to detect this
			return -1;
		}
	}
	public double getSharesAsDouble() {
		try {
			return Double.parseDouble(shares);
		} catch (NumberFormatException e) {
			// call getValidationErrors() to detect this
			return -1;
		}
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fundId == null || fundId.length() == 0) errors.add("Fund Id is required");
		if (shares == null || shares.length() == 0) errors.add("Shares number is required");
		
		if (errors.size() > 0) return errors;

		try {
			int temp = Integer.parseInt(fundId);
			if (temp < 0) errors.add("Fund Id cannot be negative");
		} catch (NumberFormatException e) {
			errors.add("Fund Id is not an integer");
		}
		
		try {
			Double temp = Double.parseDouble(shares);
			if (temp < 0) errors.add("Share number cannot be negative");
		} catch (NumberFormatException e) {
			errors.add("Share number is not an number");
		}
		
		return errors;
	}
}
