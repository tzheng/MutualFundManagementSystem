package databean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class CustomerBean {
	private int customerId;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private int zip;
	private String cash;
	private String lastTradeDate;

	private int salt = 0;
	
	public boolean checkPassword(String password) {
		System.out.println("Original password is " + this.password);
		System.out.println("hashed password is " + hash(password));
		return this.password.equals(hash(password));
	}
	
	public void setSalt(int salt){
		this.salt = salt;
	}
	
	public void setDirectPassword(String password){
		this.password = password;
	}
	
	public void setPassword(String password) {
		salt = newSalt();
		this.password = hash(password);
	}
	
	public int getSalt() {
		return salt;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
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

	public int getZip() {
		return zip;
	}

	public String getCash() {
		return cash;
	}

	public String getLastTradeDate() {
		return lastTradeDate;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public void setLastTradeDate(String lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}
	
	private String hash(String clearPassword) {
		MessageDigest md = null;
		try {
		  md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
		  throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
		}

		String saltString = String.valueOf(salt);
		
		md.update(saltString.getBytes());
		md.update(clearPassword.getBytes());
		byte[] digestBytes = md.digest();

		// Format the digest as a String
		StringBuffer digestSB = new StringBuffer();
		for (int i=0; i<digestBytes.length; i++) {
		  int lowNibble = digestBytes[i] & 0x0f;
		  int highNibble = (digestBytes[i]>>4) & 0x0f;
		  digestSB.append(Integer.toHexString(highNibble));
		  digestSB.append(Integer.toHexString(lowNibble));
		}
		String digestStr = digestSB.toString();

		return digestStr;
	}
	
	private int newSalt() {
		Random random = new Random();
		return random.nextInt(8192)+1;  // salt cannot be zero, except for uninitialized password
	}
}
