package com.transfermoney.project.TransferMoney.Business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transfermoney.project.TransferMoney.Core.Utilities.Results.*;
import com.transfermoney.project.TransferMoney.DataAccess.IAccountDal;
import com.transfermoney.project.TransferMoney.Entities.Account;
import com.transfermoney.project.TransferMoney.Entities.MoneyTransferDTO;


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
	
	@Override
	@Transactional
	public Result TransferMoney(MoneyTransferDTO moneyTransferDto) {		
		try {
			Account senderAccount = accountDal.getById(moneyTransferDto.getSenderId());
			Account receiverAccount = accountDal.getById(moneyTransferDto.getReceiverId());
			if (senderAccount.isTreasury()) {
				double exchangedMoney = exchangeMoney(receiverAccount.getCurrency(), senderAccount.getCurrency(), moneyTransferDto.getMoney());
				senderAccount.setBalance(senderAccount.getBalance() - moneyTransferDto.getMoney());
				receiverAccount.setBalance(receiverAccount.getBalance() + exchangedMoney);
				accountDal.update(senderAccount);
				accountDal.update(receiverAccount);
			}
			else if (senderAccount.getBalance() >= moneyTransferDto.getMoney() && senderAccount.getBalance() > 0) {
				double exchangedMoney = exchangeMoney(receiverAccount.getCurrency(), senderAccount.getCurrency(), moneyTransferDto.getMoney());
				senderAccount.setBalance(senderAccount.getBalance() - moneyTransferDto.getMoney());
				receiverAccount.setBalance(receiverAccount.getBalance() + exchangedMoney);
				accountDal.update(senderAccount);
				accountDal.update(receiverAccount);
			}
			else {
				return new ErrorResult("Sender Balance is not enough for this transaction.");
			}
			
			return new SuccessResult("Transfer is Successful");
		} catch (Exception e) {
			return new ErrorResult("An Error : " + e.getMessage());
		}		
	}

	@Override
	public double exchangeMoney(String baseCurrency, String exchangingCurrency, double money) throws JSONException, IOException {
		String url_str = "https://api.exchangeratesapi.io/latest?base=" + exchangingCurrency;
		JSONObject json = getRatesFromApi(url_str);
		return money * (double)json.getJSONObject("rates").get(baseCurrency);
		
		
	}

	@Override
	public JSONObject getRatesFromApi(String url) throws IOException, JSONException{
		InputStream is = new URL(url).openStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		
		try {
			String jsonText = sb.toString();
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

}
