package com.example.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bank.entity.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	@Query("from UserAccount where user.userId = :userId")
	List<UserAccount> findByUserId(int userId);
	
	UserAccount findByAccountNumber(long accountNumber);

}
