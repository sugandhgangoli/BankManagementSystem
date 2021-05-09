package com.example.bank.constants;

public enum CountryConstants {
	India("India");
private String country;
	
	private CountryConstants(String country)
	{
		this.country = country;
	}

	public String getStatus() {
		return country;
	}

	public void setStatus(String status) {
		this.country = country;
	}
}
