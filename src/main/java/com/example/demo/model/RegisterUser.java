package com.example.demo.model;

public class RegisterUser {
	String firstName;
	String lastName;
	String mobileNum;
	String aadharNum;
	String dob;

	public RegisterUser() {
	}

	public RegisterUser(String firstName, String lastName, String mobileNum, String aadharNum, String dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNum = mobileNum;
		this.aadharNum = aadharNum;
		this.dob = dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getAadharNum() {
		return aadharNum;
	}

	public void setAadharNum(String aadharNum) {
		this.aadharNum = aadharNum;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

}
