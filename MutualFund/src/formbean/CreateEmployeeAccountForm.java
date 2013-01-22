package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateEmployeeAccountForm extends FormBean{
	
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String confirmPassword;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = trimAndConvert(firstName,"<>\"");
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = trimAndConvert(lastName,"<>\"");
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password.trim();
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword.trim();
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (userName == null || userName.length() == 0) errors.add("Username is required");
		if (firstName == null || firstName.length() == 0) errors.add("First name is required");
        if (lastName == null || lastName.length() == 0) errors.add("Last name is required");
        if (password == null || password.length() == 0) errors.add("Password is required");
        if (confirmPassword == null || confirmPassword.length() == 0) errors.add("Confirm password is required");
        
        if(errors.size()>0) return errors;
        
        if(!password.equals(confirmPassword)) errors.add("Mismatching passwords");
        
		return errors;
		
		
	}
	

}