package com.transfermoney.project.TransferMoney.Business;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.transfermoney.project.TransferMoney.Core.Utilities.Results.DataResult;
import com.transfermoney.project.TransferMoney.Core.Utilities.Results.Result;
import com.transfermoney.project.TransferMoney.Entities.*;

public interface IAccountService {

	DataResult<List<Account>> getAll();
	Result add(Account account);
	Result update(Account account);
	Result delete(Account account);
	DataResult<Account> getById(int accountId);
	Result TransferMoney(MoneyTransferDTO moneyTransferDto);
	double exchangeMoney(String baseCurrency, String exchangingCurrency, double money) throws JSONException, IOException;
	JSONObject getRatesFromApi(String url) throws IOException, JSONException;
	
}
