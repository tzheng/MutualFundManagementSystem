package formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public void setLastName(String lastName) {
		this.lastName = trimAndConvert(lastName, "<>\"");
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
		this.userName = trimAndConvert(s, "<>\"");
	}

	public void setPassword(String s) {
		this.password = trimAndConvert(s.trim(), "<>\"");
	}

	public void setConfirmPassword(String s) {
		this.confirmPassword = trimAndConvert(s.trim(), "<>\"");
	}

	public void setFirstName(String s) {
		this.firstName = trimAndConvert(s, "<>\"");
	}

	public void setAddrLine1(String s) {
		this.addrLine1 = trimAndConvert(s, "<>\"");
	}

	public void setAddrLine2(String s) {
		this.addrLine2 = trimAndConvert(s, "<>\"");
	}

	public void setCity(String s) {
		this.city = trimAndConvert(s, "<>\"");
	}

	public void setState(String s) {
		this.state = trimAndConvert(s, "<>\"");
	}

	public void setZip(String zip) {
		this.zip = trimAndConvert(zip, "<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.trim().length() == 0)
			errors.add("Username is required");
		if (firstName == null || firstName.trim().length() == 0)
			errors.add("First name is required");
		if (lastName == null || lastName.trim().length() == 0)
			errors.add("Last name is required");
		if (password == null || password.trim().length() == 0)
			errors.add("Password is required");
		if (confirmPassword == null || confirmPassword.trim().length() == 0)
			errors.add("Confirm password is required");
		if (addrLine1 == null || addrLine1.trim().length() == 0)
			errors.add("Address Line 1 is required");
		if (city == null || city.trim().length() == 0)
			errors.add("City is required");
		if (state == null || state.trim().length() == 0)
			errors.add("State is required");
		if (zip == null || zip.trim().length() == 0)
			errors.add("Zipcode is required");

		if (errors.size() > 0)
			return errors;

		try {
			int zipI = Integer.parseInt(zip);
		} catch (NumberFormatException e) {
			errors.add("Zipcode should be a number");
		}

		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(userName.trim());
		boolean found = matcher.find();
		if (found) {
			errors.add("Username should not contain space(' ')");
		}
		if (userName.trim().length() > 30) {
			errors.add("Username should be less then 30 characters");
		}
		if (!userName.matches("^[a-zA-Z][0-9a-zA-Z_-@].$")) 
			errors.add("User Name should not contain non-word characters");
		
		if (firstName.matches(".*\\W.*"))
			errors.add("First Name should not contain non-word characters");

		if (firstName.trim().length() > 30 || lastName.trim().length() > 30) {
			errors.add("Firstname/Lastname should be less then 30 characters");
		}

		if (!zip.trim().matches("^\\d{5}(-\\d{4})?$"))
			errors.add("Zip code is not in the correct format");

		if (!password.equals(confirmPassword))
			errors.add("Passwords do not match! Please re-enter");
		if (lastName.matches(".*\\W.*"))
			errors.add("Last Name should not contain non-word characters");

		return errors;
	}

}
