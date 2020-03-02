package com.transfermoney.project.TransferMoney.Core.Utilities.Results;

public class ErrorResult extends Result {
	
	public ErrorResult(String message) {
		super(false, message);
	}
	
	public ErrorResult() {
		super(false);
	}

}
