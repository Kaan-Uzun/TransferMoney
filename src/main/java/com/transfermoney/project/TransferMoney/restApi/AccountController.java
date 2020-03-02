package com.transfermoney.project.TransferMoney.restApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transfermoney.project.TransferMoney.Business.IAccountService;
import com.transfermoney.project.TransferMoney.Core.Utilities.Results.*;
import com.transfermoney.project.TransferMoney.Entities.Account;
import com.transfermoney.project.TransferMoney.Entities.MoneyTransferDTO;

@RestController
@RequestMapping("/api")
public class AccountController {
	
	private IAccountService accountService;

	@Autowired
	public AccountController(IAccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping("/accounts")
	public ResponseEntity<?> get(){
		
		DataResult<List<Account>> result = accountService.getAll();
		
		if (result.Success) {
			return new ResponseEntity<>(result.Data, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/accounts/add")
	public ResponseEntity<?> add(@RequestBody Account account){
		
		Result result = accountService.add(account);
		
		if (result.Success) {
			return new ResponseEntity<>(result.Message, HttpStatus.OK);
		}
		return new ResponseEntity<>("Bir Hata Oluştu.", HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping("/accounts/update")
	public ResponseEntity<?> update(@RequestBody Account account){
		Result result = accountService.update(account);
		
		if (result.Success) {
			return new ResponseEntity<>(result.Message, HttpStatus.OK);
		}
		return new ResponseEntity<>("Bir Hata Oluştu.", HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping("/accounts/delete")
	public ResponseEntity<?> delete(@RequestBody Account account){
		Result result = accountService.delete(account);
		
		if (result.Success) {
			return new ResponseEntity<>(result.Message, HttpStatus.OK);
		}
		return new ResponseEntity<>("Bir Hata Oluştu.", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/accounts/{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		
		DataResult<Account> result = accountService.getById(id);
		
		if (result.Success) {
			return new ResponseEntity<>(result.Data, HttpStatus.OK);
		}
		return new ResponseEntity<>(result.Message, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/TransferMoney")
	public ResponseEntity<?> TransferMoney(@RequestBody MoneyTransferDTO moneyTransferDto){
		
		Result result = accountService.TransferMoney(moneyTransferDto);
		
		if (result.Success) {
			return new ResponseEntity<>(result.Message, HttpStatus.OK);
		}
		return new ResponseEntity<>(result.Message, HttpStatus.BAD_REQUEST);
	}
}
