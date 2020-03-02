package com.transfermoney.project.TransferMoney.Business;

import java.util.List;

import com.transfermoney.project.TransferMoney.Core.Utilities.Results.DataResult;
import com.transfermoney.project.TransferMoney.Core.Utilities.Results.Result;
import com.transfermoney.project.TransferMoney.Entities.*;

public interface IAccountService {

	DataResult<List<Account>> getAll();
	Result add(Account account);
	Result update(Account account);
	Result delete(Account account);
	DataResult<Account> getById(int accountId);
	
}
