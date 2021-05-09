package com.example.bank.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.entity.UserAccount;
import com.example.bank.entity.UserBankStatement;
import com.example.bank.repository.UserBankStatementRepository;
import com.example.bank.service.UserBankStatementService;

@Service
public class UserBankStatementServiceImpl implements UserBankStatementService {
	
	@Autowired
	private UserBankStatementRepository userBankStatementRepository;

	@Override
	public void insertCreditLine(UserAccount account, double amount) {
		UserBankStatement statement = new UserBankStatement();
		statement.setCredit(amount);
		statement.setUserAccount(account);
		this.userBankStatementRepository.save(statement);
	}

	@Override
	public void insertDebitLine(UserAccount account, double amount) {
		UserBankStatement statement = new UserBankStatement();
		statement.setDebit(amount);
		statement.setUserAccount(account);
		this.userBankStatementRepository.save(statement);
	}

	@Override
	public List<UserBankStatement> bankStatement(long accountNumber, Date fromDate, Date toDate) {
		return this.userBankStatementRepository.getBankStatementByDateRange(accountNumber, fromDate, toDate);
	}

}
