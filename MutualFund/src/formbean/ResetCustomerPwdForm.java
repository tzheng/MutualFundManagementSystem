package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResetCustomerPwdForm extends FormBean {
	private String confirmPassword;
	private String newPassword;
	private String userName;
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (userName == null || userName.trim().length() == 0) {
			errors.add("Username is required");
		}
		
		if (newPassword == null || newPassword.trim().length() == 0) {
			errors.add("New Password is required");
		}
		
		if (confirmPassword == null || confirmPassword.trim().length() == 0) {
			errors.add("Confirm Password is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!newPassword.equals(confirmPassword)) {
			errors.add("Passwords do not match");
		}

		return errors;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword.trim();
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = trimAndConvert(userName, "<>\"");
	}
}
