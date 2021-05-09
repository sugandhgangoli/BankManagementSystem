package com.example.bank.service;

import java.sql.Date;
import java.util.List;

import com.example.bank.entity.UserAccount;
import com.example.bank.entity.UserBankStatement;

public interface UserBankStatementService {
	
	public void insertCreditLine(UserAccount account, double amount);
	
	public void insertDebitLine(UserAccount account, double amount);
	
	public List<UserBankStatement> bankStatement(long accountNumber, Date fromDate,  Date toDate);
}
