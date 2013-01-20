package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CustomerIdForm extends FormBean {
	private String customerId;
	
	public int getCustomerIdasInt() {
		try {
			return Integer.parseInt(customerId);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (customerId == null || customerId.length() == 0) {
			errors.add("customer Id is required");
			return errors;
		}
		
		try {
			Integer.parseInt(customerId);
		} catch (NumberFormatException e) {
			errors.add("Customer Id is Invalid (Must be an Integer).");
		}
		
		return errors;
	}
}
