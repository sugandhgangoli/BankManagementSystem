package com.example.bank.service.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.bank.entity.User;
import com.example.bank.entity.UserAccount;
import com.example.bank.exception.APIException;
import com.example.bank.exception.ErrorDetails;
import com.example.bank.repository.UserAccountRepository;
import com.example.bank.service.UserAccountService;
import com.example.bank.service.UserBankStatementService;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private UserBankStatementService userBankStatementService;

	@Override
	public UserAccount createAccount(User user) {
		UserAccount account = new UserAccount();
		Random random = new Random();
		int randomAccountNumber = random.nextInt(1000000000) + 1;
		account.setUser(user);
		account.setAmount(5000);
		account.setAccountNumber(randomAccountNumber);
		account.setAccountType("Saving");
		account = this.userAccountRepository.save(account);
		this.userBankStatementService.insertCreditLine(account, account.getAmount());
		return account;
	}

	@Override
	public boolean creditAccount(long accountNumber, double amount) {
		if (amount <= 0)
			return false;
		UserAccount account = this.userAccountRepository.findByAccountNumber(accountNumber);
		account.setAmount(account.getAmount() + amount);
		this.userBankStatementService.insertCreditLine(account, amount);
		return true;
	}

	@Override
	public boolean debitAccount(long accountNumber, double amount) {
		if (amount <= 0)
			return false;
		UserAccount account = this.userAccountRepository.findByAccountNumber(accountNumber);
		if (account.getAmount() < amount)
			return false;
		account.setAmount(account.getAmount() - amount);
		this.userBankStatementService.insertDebitLine(account, amount);
		return true;
	}

	@Override
	public boolean transferFund(long accountNumber1, long accountNumber2, double amount) throws APIException {
		try {
			if (accountNumber1 != accountNumber2) {
				UserAccount fromAccount = this.userAccountRepository.findByAccountNumber(accountNumber1);
				UserAccount toAccount = this.userAccountRepository.findByAccountNumber(accountNumber2);
				debitAccount(toAccount.getAccountNumber(), amount);
				creditAccount(toAccount.getAccountNumber(), amount);
				return true;
			}
		} catch (DataIntegrityViolationException exception) {
			
		}
		return false;
	}

	@Override
	public List<UserAccount> getUserAccount(int userId) {
		return this.userAccountRepository.findByUserId(userId);

	}

}
