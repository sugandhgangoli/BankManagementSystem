package com.example.bank.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "bankStatement")
public class UserBankStatement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankStatementId;
	
	private double credit;
	
	private double debit;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private UserAccount userAccount;

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public int getBankStatementId() {
		return bankStatementId;
	}

	public void setBankStatementId(int bankStatementId) {
		this.bankStatementId = bankStatementId;
	}

	public UserBankStatement() {
		super();
		// TODO Auto-generated constructor stub
	}

}
