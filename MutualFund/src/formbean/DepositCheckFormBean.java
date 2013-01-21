package formbean;

import java.util.ArrayList;
import java.util.List;

public class DepositCheckFormBean {
private String depositAmount;
	
	public String getDepositAmount() {
		return depositAmount;
	}
	
	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}
	
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (depositAmount == null || depositAmount.length() == 0) {
			errors.add("Please enter the deposit Amount");
		}
		
		if (depositAmount.matches(".*[<>\"].*")) {
	            errors.add("Input should contain only valid numerical value");
	     }

		try {
			int buywithdrawamount = Integer.parseInt(depositAmount);
			if (buywithdrawamount <= 0) {
                errors.add("Deposit amount value should be a positive value");
			}
		} catch (NumberFormatException e) {
			errors.add("Deposit amount is not an integer");
		}

		return errors;
	}

}
