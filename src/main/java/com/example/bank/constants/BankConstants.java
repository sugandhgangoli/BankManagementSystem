package com.example.bank.constants;

public enum BankConstants {
	ACTIVE("Active"),
	INACTIVE("Inactive");
	
	private String status;
	
	private BankConstants(String status)
	{
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
