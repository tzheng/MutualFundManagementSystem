package databean;

import java.util.Date;

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
	private double cash;
	private String lastTradeDate;
	
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
	public double getCash() {
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
	public void setPassword(String password) {
		this.password = password;
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
	public void setCash(double cash) {
		this.cash = cash;
	}
	public void setLastTradeDate(String lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}
}
