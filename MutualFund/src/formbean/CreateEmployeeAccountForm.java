package formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		this.userName = trimAndConvert(userName, "<>\"");
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
		this.password = trimAndConvert(password.trim(), "<>\"");
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = trimAndConvert(confirmPassword.trim(), "<>\"");
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (userName == null || userName.length() == 0) errors.add("Username is required");
		if (firstName == null || firstName.length() == 0) errors.add("First name is required");
        if (lastName == null || lastName.length() == 0) errors.add("Last name is required");
        if (password == null || password.length() == 0) errors.add("Password is required");
        if (confirmPassword == null || confirmPassword.length() == 0) errors.add("Confirm password is required");
        
        if(errors.size()>0) return errors;
        
        if (userName.trim().length() > 30) {
        	errors.add("Username should be less then 30 characters");
        }
        if (!userName.matches("^[a-zA-Z][0-9a-zA-Z_@-]*$")) 
			errors.add("Username should only contain characters and numbers and start with character");
        
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(userName.trim());
        boolean found = matcher.find();
        if (found) {
        	errors.add("Username should not contain space(' ')");
        }
        
        if (firstName.matches(".*\\W.*")) errors.add("First Name should not contain non-word characters");
        if (lastName.matches(".*\\W.*")) errors.add("Last Name should not contain non-word characters");
        
        if (firstName.trim().length() > 30 || lastName.trim().length() > 30) {
        	errors.add("First Name/ Last Name should be less then 30 characters");
        }
        
        if(!password.equals(confirmPassword)) errors.add("Passwords do not match! Please re-enter");
        
		return errors;
		
		
	}
	

}
