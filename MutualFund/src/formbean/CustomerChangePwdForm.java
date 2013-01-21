package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CustomerChangePwdForm extends FormBean {

	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword.trim();
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword.trim();
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword.trim();
	}

	public List<String> getValidationErrors(){
		List<String> errors = new ArrayList<String>();
		
		if (oldPassword == null || oldPassword.length() == 0) {
			errors.add("Old Password is required");
		}
		
		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
		}
		
		if (confirmNewPassword == null || confirmNewPassword.length() == 0) {
			errors.add("Confirm New Password is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (oldPassword.equals(newPassword)) {
			errors.add("New password cannot be as same as previous one");
		}
		
		
		if (!newPassword.equals(confirmNewPassword)) {
			errors.add("Passwords do not match");
		}
		
		return errors;
		
	}
	
}
