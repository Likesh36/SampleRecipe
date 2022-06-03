package com.recipe.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends User {

	@Column
	private String customerName;
	@Column
	private String mobileNumber;
	@Column
	private String emailId;
	@Column
	private LocalDate dateOfBirth;
	@Column
	private String gender;
	@Column
	private String nationality;

	public Customer() {
		super();
	}

	public Customer(int userId, String username, String password, String role, String customerName, String mobileNumber,
			String emailId, LocalDate dateOfBirth, String gender, String nationality) {
		super(userId, username, password, role);
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.nationality = nationality;
	}

	public Customer(String username, String password, String role, String customerName, String mobileNumber) {
		super(username,password,role);
		
	}

	public Customer(String username, String password, String role, String customerName, String mobileNumber, String emailId,
			LocalDate dateOfBirth, String gender, String nationality) {
		super(username,password,role);
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.nationality = nationality;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
