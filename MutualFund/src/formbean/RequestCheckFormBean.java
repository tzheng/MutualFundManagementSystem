package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckFormBean extends FormBean{
	private String withdrawamount;
	
	public String getwithdrawamount() {
		return withdrawamount;
	}
	
	public void setwithdrawamount(String withdrawamount) {
		this.withdrawamount = withdrawamount;
	}
	
	public double getAmountAsDouble() {
		double buywithdrawamount;
		
		try {
			buywithdrawamount = Double.parseDouble(withdrawamount);
			return buywithdrawamount;
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (withdrawamount == null || withdrawamount.length() == 0) {
			errors.add("Amount is required");
		}
		
		if (withdrawamount.matches(".*[<>\"].*")) {
	            errors.add("Input should contain only valid numerical value");
	     }

		try {
			double buywithdrawamount = Double.parseDouble(withdrawamount);
			
			if (buywithdrawamount > Math.pow(10, 9) || buywithdrawamount < 0.01) {
                errors.add("Amount should between $0.01 dollar and $1,000,000,000.00 (one billion) dollars");
			}
		} catch (NumberFormatException e) {
			errors.add("Amount is not a number!! Please enter a numerical value");
		}

		return errors;
	}
}
