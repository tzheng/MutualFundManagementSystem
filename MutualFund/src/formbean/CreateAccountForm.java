package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

public class CreateAccountForm extends FormBean {

	private String userName;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private String zip;
	

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddrLine1() {
		return addrLine1;
	}
	
	public String getAddrLine2() {
		return addrLine2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}
	
	public String getZip() {
		return zip;
	}
	
	public int getZipAsInt() {
		return Integer.parseInt(zip);
	}
	
	
	
	public void setUserName(String s) {
		this.userName = trimAndConvert(s,"<>\"");
	}

	public void setPassword(String s) {
		this.password = s.trim();
	}
	
	public void setConfirmPassword(String s) {
		this.confirmPassword = s.trim();
	}

	public void setFirstName(String s) {
		this.firstName = trimAndConvert(s,"<>\"");
	}

	public void setAddrLine1(String s) {
		this.addrLine1 = trimAndConvert(s,"<>\"");
	}
	
	public void setAddrLine2(String s) {
		this.addrLine2 = trimAndConvert(s,"<>\"");
	}
	
	public void setCity(String s) {
		this.city = trimAndConvert(s,"<>\"");
	}
	
	public void setState(String s) {
		this.state = trimAndConvert(s,"<>\"");
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (userName == null || userName.length() == 0) errors.add("Email address is required");
        if (firstName == null || firstName.length() == 0) errors.add("First name is required");
        if (lastName == null || lastName.length() == 0) errors.add("Last name is required");
        if (password == null || password.length() == 0) errors.add("Password is required");
        if (confirmPassword == null || confirmPassword.length() == 0) errors.add("Confirm password is required");
        if (addrLine1 == null || addrLine1.length() == 0) errors.add("Address Line 1 is required");
        if (addrLine2 == null || addrLine2.length() == 0) errors.add("Address Line 2 is required");
        if (city == null || city.length() == 0) errors.add("City is required");
        if (state == null || state.length() == 0) errors.add("State is required");
        if (zip == null || zip.length() == 0) errors.add("Zip is required");
        
        if (errors.size() > 0) return errors;
        
        if(!password.equals(confirmPassword)) errors.add("Mismatching password");
		
        return errors;
    }
	
}
