package com.transfermoney.project.TransferMoney.DataAccess;

import java.util.List;

import com.transfermoney.project.TransferMoney.Entities.*;

public interface IAccountDal {
	List<Account> getAll();
	void add(Account account);
	void update(Account account);
	void delete(Account account);
	Account getById(int accountId);

}
