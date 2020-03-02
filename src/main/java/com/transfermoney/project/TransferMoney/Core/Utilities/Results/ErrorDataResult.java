package com.transfermoney.project.TransferMoney.Core.Utilities.Results;

public class ErrorDataResult<T> extends DataResult<T> {
	
	public ErrorDataResult(T data, boolean success, String message) {
		super(data, success, message);
	}
	
	public ErrorDataResult(T data, boolean success) {
		super(data, success);
	}

}
