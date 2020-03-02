package com.transfermoney.project.TransferMoney.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transfermoney.project.TransferMoney.Core.Utilities.Results.*;
import com.transfermoney.project.TransferMoney.DataAccess.IAccountDal;
import com.transfermoney.project.TransferMoney.Entities.Account;


@Service
public class AccountManager implements IAccountService {
	
	private IAccountDal accountDal;

	@Autowired
	public AccountManager(IAccountDal accountDal) {
		this.accountDal = accountDal;
	}

	@Override
	public DataResult<List<Account>> getAll() {
		return new SuccessDataResult<List<Account>>(accountDal.getAll(), true);
	}

	@Override
	@Transactional
	public Result add(Account account) {
		accountDal.add(account);
		return new SuccessResult("Account is added.");
	}

	@Override
	@Transactional
	public Result update(Account account) {
		accountDal.update(account);
		return new SuccessResult("Account is updated.");
	}

	@Override
	@Transactional
	public Result delete(Account account) {
		accountDal.delete(account);
		return new SuccessResult("Account is deleted.");
	}

	@Override
	@Transactional
	public DataResult<Account> getById(int accountId) {
		
		Account tempResult = accountDal.getById(accountId);
		
		if (tempResult != null) {
			return new SuccessDataResult<Account>(accountDal.getById(accountId), true);
		}
		else {
			return new ErrorDataResult<Account>(null, false, "Account is not found");
		}
				
	}

}
