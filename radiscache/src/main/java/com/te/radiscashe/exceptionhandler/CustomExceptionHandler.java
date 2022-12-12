package com.te.radiscashe.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.radiscashe.exception.CustomException;
import com.te.radiscashe.response.Response;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Response> exceptionHandler(CustomException e) {

		return new ResponseEntity<Response>(new Response(true, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
}
