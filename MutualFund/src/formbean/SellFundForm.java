/**
 * @author Team Snipers (Team 1)
 * Jan 24, 2013
 */

package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFundForm extends FormBean {
	private String fundName;
	private String shares;
	
	public String getFundName() {
		return fundName;
	}
	public String getShares() {
		return shares;
	}
	
	public void setFundName(String fundName) {
		this.fundName = trimAndConvert(fundName, "<>\"");
	}
	public void setShares(String shares) {
		this.shares = trimAndConvert(shares, "<>\"");
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
		
		if (fundName == null || fundName.length() == 0) errors.add("Fund Name is required");
		if (shares == null || shares.length() == 0) errors.add("Shares number is required");
		
		if (errors.size() > 0) return errors;

		try {
			Double temp = Double.parseDouble(shares);
			if (temp < 0) errors.add("Share number cannot be negative");
		} catch (NumberFormatException e) {
			errors.add("Share number is not an number");
		}
		
		return errors;
	}
}
