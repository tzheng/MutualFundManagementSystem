package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyFundForm extends FormBean {
	private String fundName;
	private String amount;
	
	public String getFundName() {
		return fundName;
	}
	
	public double getAmountAsDouble() {
		try {
			return Double.parseDouble(amount);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = trimAndConvert(amount, "<>\"");
	}

	public void setFundName(String fundName) {
		this.fundName = trimAndConvert(fundName, "<>\"");
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fundName == null || fundName.trim().length() == 0) {
			errors.add("Fund Name is required!");
		}
		
		if (fundName.trim().length() > 30) {
			errors.add("Fund Name should be less than 30 characters");
		}
		
		if (amount == null || amount.trim().length() == 0) {
			errors.add("Amount is required!");
		}
		
		if (errors.size() != 0) {
			return errors;
		}
		
		double amountD;
		try {
			amountD = Double.parseDouble(amount);
		} catch (NumberFormatException e) {
			errors.add("Amount should be a number");
			return errors;
		}
		

		if (amountD > Math.pow(10, 9) || amountD < 0.01) {
			errors.add("Amount should between $0.01 dollar and $1,000,000,000.00 (one billion) dollars");
		}
		
		return errors;
	}
}
