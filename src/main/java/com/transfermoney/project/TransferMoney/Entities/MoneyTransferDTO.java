package com.transfermoney.project.TransferMoney.Entities;

import com.transfermoney.project.TransferMoney.Core.Utilities.Entities.IDto;

public class MoneyTransferDTO implements IDto {
	
	private int senderId;
	private double money;
	private int receiverId;
	
	public MoneyTransferDTO(int senderId, double money, int receiverId) {
		this.senderId = senderId;
		this.money = money;
		this.receiverId = receiverId;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

}
