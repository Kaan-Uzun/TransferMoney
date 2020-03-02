package com.transfermoney.project.TransferMoney;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.transfermoney.project.TransferMoney.Business.IAccountService;
import com.transfermoney.project.TransferMoney.Core.Utilities.Results.Result;
import com.transfermoney.project.TransferMoney.DataAccess.IAccountDal;
import com.transfermoney.project.TransferMoney.Entities.Account;
import com.transfermoney.project.TransferMoney.Entities.MoneyTransferDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
class TransferMoneyTests {
	
	@MockBean
	private IAccountDal accountDal;
	
	
	@Autowired
	private IAccountService accountService;
	
	

	@Test
	public void getAccountTest() {		
		when(accountDal.getAll()).thenReturn(Stream
				.of(new Account(20, "TestAccount", "TRY", 10000, true), new Account(21, "TestAccount2", "EUR", 50000, false)).collect(Collectors.toList()));
		assertEquals(2, accountService.getAll().Data.size());
	}
	
	
	@Test
	public void getSpecificAccountTest() {		
		
		Account testAcc = new Account(20, "TestAccount", "TRY", 10000, true);
		
		when(accountDal.getById(testAcc.getId())).thenReturn(testAcc);
		assertEquals(testAcc, accountService.getById(testAcc.getId()).Data);
	}
	
	@Test
	public void moneyTransferCheck() {		
		MoneyTransferDTO moneyTransferDto = spy(new MoneyTransferDTO(1, 100000, 3));
		Result result = accountService.TransferMoney(moneyTransferDto);
		assertEquals(result.Success, accountService.TransferMoney(moneyTransferDto).Success);
	}
	
	@Test
	public void exchangedMethodCheck() throws JSONException, IOException {	
		// It's available to check another currency as CAD, TRY, AUD etc...
		// Why we are using only 1 USD or 1 TRY, because currency always changing every time when we check,
		// and when we check the 1 USD or 1 TRY, result can always return again 1 USD or 1 TRY
		double exchangedMoney = accountService.exchangeMoney("USD", "USD", 1);
		System.out.println(exchangedMoney);
		assertEquals(exchangedMoney, 1);
	}
	
}
