package com.example.bank.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.entity.UserAccount;
import com.example.bank.entity.UserBankStatement;
import com.example.bank.exception.APIException;
import com.example.bank.service.UserAccountService;
import com.example.bank.service.UserBankStatementService;

import static java.time.temporal.TemporalAdjusters.*;

@RestController
@RequestMapping("/api/account")
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private UserBankStatementService userBankStatementService;

	@GetMapping("/{id}")
	public List<UserAccount> getUserAccount(@PathVariable(value = "id") int userId) {
		return this.userAccountService.getUserAccount(userId);
	}

	@PostMapping("/credit")
	public boolean creditAccount(@RequestParam long accountNumber, @RequestParam int amount) {
		return this.userAccountService.creditAccount(accountNumber, amount);
	}

	@PostMapping("/debit")
	public boolean debitAccount(@RequestParam long accountNumber, @RequestParam int amount) {
		return this.userAccountService.debitAccount(accountNumber, amount);
	}

	
	@PostMapping("/transfer")
	  public boolean transferFund(@RequestParam long accountNumber1, @RequestParam long accountNumber2, @RequestParam double amount) throws APIException {
	  return this.userAccountService.transferFund(accountNumber1, accountNumber2, amount);
	  }
	 

	@SuppressWarnings("deprecation")
	@GetMapping("/statement")
	public List<UserBankStatement> bankStatement(@RequestParam long accountId, @RequestParam String fromDate, @RequestParam String toDate) {
		String arr1[] = fromDate.split("/");
		if(arr1.length!=2 || (Integer.parseInt(arr1[0]) < 1 && Integer.parseInt(arr1[0]) > 12) || (Integer.parseInt(arr1[1]) < 2000 && Integer.parseInt(arr1[1]) > new java.util.Date().getYear()))
			throw new APIException("Incorrect From Date Format. Use format like '1/2021'");
		String arr2[] = toDate.split("/");
		if(arr2.length!=2 || (Integer.parseInt(arr2[0]) < 1 && Integer.parseInt(arr2[0]) > 12) || (Integer.parseInt(arr2[1]) < 2000 && Integer.parseInt(arr2[1]) > new java.util.Date().getYear()))
			throw new APIException("Incorrect To Date Format. Use format like '1/2021'");
		
		Date formattedFromDate = Date.valueOf(LocalDate.of(Integer.parseInt(arr1[1]), Integer.parseInt(arr1[0]), 1));
		
		LocalDate initial = LocalDate.of(Integer.parseInt(arr2[1]), Integer.parseInt(arr2[0]), 1);
		LocalDate end = initial.with(lastDayOfMonth());
		Date formattedToDate = Date.valueOf(end);
		
		if(formattedToDate.before(formattedFromDate))
			throw new APIException("To Date cannot be less than from date");
		return this.userBankStatementService.bankStatement(accountId, formattedFromDate, formattedToDate);
	}

}
