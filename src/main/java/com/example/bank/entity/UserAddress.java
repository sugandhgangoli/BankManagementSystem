package com.example.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.bank.constants.BankConstants;
import com.example.bank.constants.CountryConstants;
import com.sun.istack.NotNull;

@Entity
@Table(name = "address")
public class UserAddress {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	
	private String city;
	private String state;
	private CountryConstants country;
	private String addressLine1;
	private String addressLine2;
	
	

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;



	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public CountryConstants getCountry() {
		return country;
	}
	
}
