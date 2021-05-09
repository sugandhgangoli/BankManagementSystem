package com.example.bank.service;

import java.util.List;

import com.example.bank.entity.User;
import com.example.bank.entity.UserAccount;
import com.example.bank.exception.APIException;


public interface UserAccountService {
	
	public UserAccount createAccount(User user);
	public boolean creditAccount(long accountNumber, double amount)throws APIException; 
	public boolean debitAccount(long accountNumber, double amount)throws APIException; 
	public boolean transferFund(long accountNumber1, long accountNumber2, double amount)throws APIException; 
	public List<UserAccount> getUserAccount(int userId);
	 

}
