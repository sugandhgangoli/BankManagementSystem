package com.example.bank.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.bank.entity.UserBankStatement;

public interface UserBankStatementRepository extends JpaRepository<UserBankStatement, Integer> {

	@Query("from UserBankStatement where userAccount.accountNumber =:accountNumber and createDate between :fromDate and :toDate")
	public List<UserBankStatement> getBankStatementByDateRange(long accountNumber, Date fromDate, Date toDate);
}
