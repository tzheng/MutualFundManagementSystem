package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CustomerIdForm extends FormBean {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (username == null || username.trim().length() == 0) {
			errors.add("Username is required!");
		}
		
		if (username.trim().length() > 30) {
			errors.add("Username should be less then 30 characters.");
		}
		
		return errors;
	}
}


