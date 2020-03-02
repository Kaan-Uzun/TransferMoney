package com.transfermoney.project.TransferMoney.Entities;

import javax.persistence.*;

import com.transfermoney.project.TransferMoney.Core.Utilities.Entities.IEntity;

@Entity
@Table(name="account")
public class Account {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="currency")
	private String currency;
	@Column(name="balance")
	private double balance;
	@Column(name="treasury")
	private boolean treasury;
	
	public Account(int id, String name, String currency, double balance, boolean treasury) {
		super();
		this.id = id;
		this.name = name;
		this.currency = currency;
		this.balance = balance;
		this.treasury = treasury;
	}

	public Account() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isTreasury() {
		return treasury;
	}

	public void setTreasury(boolean treasury) {
		this.treasury = treasury;
	}

}
