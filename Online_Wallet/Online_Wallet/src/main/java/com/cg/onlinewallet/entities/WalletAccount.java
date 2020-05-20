package com.cg.onlinewallet.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "WalletAccount")
public class WalletAccount implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq")
	private Integer accountID;
	@Column(name="balance",precision=2)
	private Double accountBalance;

	public enum status {
		active, non_active
	};

	@Enumerated(EnumType.STRING)
	@Column(name="user_status")
	private status userStatus = status.non_active;


	public Integer getAccountID() {
		return accountID;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public WalletAccount(Double accountBalance, status userStatus) {
		super();

		this.accountBalance = accountBalance;
		this.userStatus = userStatus;
	}

	public status getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(status userStatus) {
		this.userStatus = userStatus;
	}

	

	
	public WalletAccount() {
		// TODO Auto-generated constructor stub
	}

}
