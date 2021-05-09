package com.example.bank.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.bank.constants.BankConstants;
import com.sun.istack.NotNull;

@Entity
@Table(name = "user")
public class User {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@NotNull
	private String firstName;

	private String lastName;

	@Column(unique = true)
	@Email(regexp = ".+@.+\\..+", message = "Email should be valid")
	private String emailId;

	@Column(unique = true)
	private String panNumber;

	/*
	 * @Column(columnDefinition = "varchar(6) default 'Active'") private String
	 * status;
	 */
	private BankConstants status;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date modifiedDate;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private UserAddress userAddress;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserAccount> userAccount;

	public User() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public BankConstants getStatus() {
		return status;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void addUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
		userAddress.setUser(this);
	}

	public void removeUserAddress() {
		if (userAddress != null) {
			userAddress.setUser(null);
		}
		this.userAddress = null;
	}

}
