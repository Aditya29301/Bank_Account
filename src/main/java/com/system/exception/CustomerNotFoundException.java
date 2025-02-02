package com.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
	private static long serialVersionUID = 1L;
	
	public CustomerNotFoundException(String message)
	{
		super(message);
	}

}
