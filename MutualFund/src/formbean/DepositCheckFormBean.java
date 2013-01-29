package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositCheckFormBean extends FormBean{
	private String userName;
	private String amount;
	
	public double getAmountAsDouble() {
		try {
			return Double.parseDouble(amount);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	public String getUserName() {
		return userName;
	}

	public String getAmount() {
		return amount;
	}

	public void setUserName(String userName) {
		this.userName = trimAndConvert(userName,"<>\"");
	}

	public void setAmount(String amount) {
		this.amount = trimAndConvert(amount,"<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (amount == null || amount.trim().length() == 0) {
			errors.add("Deposit Amount is required");
		}
		
		if (userName == null || userName.trim().length() == 0) {
			errors.add("Username is required");
		}
		
		if (amount.matches(".*[<>\"].*")) {
	            errors.add("Input should contain only valid numerical value");
	     }

		try {
			double amountD = Double.parseDouble(amount);
			if (amountD <= 0) {
	            errors.add("Deposit amount value should be a positive value");
			}
			if (amountD > 1000000000 || amountD < 1) {
				errors.add("Amount should between $1 (one) dollar to 10,000,000,000 (one billion) dollars");
			}
		} catch (NumberFormatException e) {
			errors.add("Deposit amount is not a number!! Please enter a numerical value");
		}
		
		return errors;
	}

}
