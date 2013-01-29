package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ChangePasswordForm extends FormBean{
	private String oldPassword;
	private String confirmPassword;
	private String newPassword;
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (oldPassword == null || newPassword.trim().length() == 0) {
			errors.add("Old Password is required");
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
			errors.add("Passwords do not match! Please re-enter");
		}

		if (oldPassword.equals(newPassword))  {
			errors.add("New password cannot be as same as the current password");
		}
			
		return errors;
	}
	
	public String getOldPassword()     { return oldPassword;     }
	public String getConfirmPassword() { return confirmPassword; }
	public String getNewPassword()     { return newPassword;     }
	
	public void setConfirmPassword(String s) { confirmPassword = s.trim(); }
	public void setNewPassword(String s)     { newPassword     = s.trim(); }
	public void setOldPassword(String s)     { oldPassword     = s.trim(); }
}
