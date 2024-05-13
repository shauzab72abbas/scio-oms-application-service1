/*
 * Copyright (c) 1888-2020 CN, Inc.
 * 935 de La Gauchetière Street West, Montreal, Quebec, H3B 2M9, CANADA.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of CN
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with CN.
 */
package ca.cn.scio.application.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;

import ca.cn.scio.application.exception.BusinessException;
import ca.cn.scio.application.exception.Error;
import ca.cn.scio.application.exception.ErrorCode;
import ca.cn.scio.application.exception.ErrorResponse;
import ca.cn.scio.application.exception.MultipleErrorsException;
import ca.cn.scio.application.exception.NotFoundException;
import ca.cn.scio.application.exception.SystemException;
import ca.cn.scio.application.exception.UnProcessable;

/**
 * This class handles the possible exceptions occurred
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String RESOURCE_REQUEST_MESSAGE = "Resource not connected";
	private static final String NO_HANDLER_MESSAGE = "Invalid Handler - " ;


	private ErrorResponse setErrorResponse(String msg , ErrorCode errorCode , String description){
		Error error = new Error();
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(msg) ;
		error.setError(errorCode) ;
		error.setDescription(description);
		List<Error> errors = new ArrayList<>();
		errors.add(error);
		errorResponse.setErrors(errors);
		return errorResponse ;

	}
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleMissingBadRequest(
			MethodArgumentNotValidException exception) {
		List<String> message = Arrays.asList(exception.getMessage().split("default message"));
		String field = message.get(1).replace("[", "").replace("]", "").replace(";", "");
		String suffix = message.get(2).replace("[", "").replace("]", "");

		ErrorResponse errorResponse = setErrorResponse(ErrorCode.SCIO002.getDescription() ,ErrorCode.SCIO002 , field + suffix) ;
		return new ResponseEntity<>(errorResponse, BAD_REQUEST);
	}


	@ExceptionHandler(SystemException.class)
	public ResponseEntity<Object> handleSystemException( SystemException exception) {
		ErrorResponse errorResponse = setErrorResponse(ErrorCode.SCIO001.getDescription() ,ErrorCode.SCIO001 , ErrorCode.SCIO001.getDescription()) ;

		LOGGER.error(exception.getMessage() , exception.getCause());
		return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
	}


	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException( BusinessException exception) {
		ErrorResponse errorResponse = setErrorResponse(exception.getMessage() ,exception.getErrorCode() , exception.getErrorCode().getDescription()) ;

		LOGGER.error(exception.getMessage() , exception.getCause());
		return new ResponseEntity<>(errorResponse, BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException( NotFoundException exception) {
		ErrorResponse errorResponse = setErrorResponse(exception.getMessage() ,exception.getErrorCode() , exception.getErrorCode().getDescription()) ;
		LOGGER.error(exception.getMessage() , exception.getCause());
		return new ResponseEntity<>(errorResponse, NOT_FOUND);
	}

	@ExceptionHandler(UnProcessable.class)
	public ResponseEntity<Object> handleNotProcessable( UnProcessable exception) {
		ErrorResponse errorResponse = setErrorResponse(exception.getMessage() ,exception.getErrorCode() , exception.getErrorCode().getDescription()) ;
		LOGGER.error(exception.getMessage() , exception.getCause());
		return new ResponseEntity<>(errorResponse, UNPROCESSABLE_ENTITY);
	}



	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<Object> handleInvalidBadRequest(
			HttpMessageNotReadableException exception) {

		ErrorResponse errorResponse = setErrorResponse(ErrorCode.SCIO002.getDescription() ,ErrorCode.SCIO002 , exception.getMessage()) ;

		LOGGER.error(exception.getMessage());
		return new ResponseEntity<>(errorResponse,BAD_REQUEST);
	}


	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<Object> handleMissingHeaderException(MissingRequestHeaderException exception) {
		ErrorResponse errorResponse = setErrorResponse(ErrorCode.SCIO004.getDescription() ,ErrorCode.SCIO004 , ErrorCode.SCIO004.getDescription()) ;
		LOGGER.error(exception.getMessage() , exception.getCause()) ;
		return new ResponseEntity<>(errorResponse, BAD_REQUEST);
	}


	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException exception) throws ParseException {
		LOGGER.error("HttpClientErrorException - " ,exception.getRootCause());
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(exception.getResponseBodyAsString());
		return new ResponseEntity<>(json, exception.getStatusCode());

	}

	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<Object> handleHttpServerErrorException(
																 HttpServerErrorException exception) throws ParseException {
		LOGGER.error("HttpServerErrorException - " , exception.getCause());

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(exception.getResponseBodyAsString());
		return new ResponseEntity<>(json, exception.getStatusCode());

	}
	/**
	 * Error handler for resource(data-layer/firestore/big-query) not connected
	 */
	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<ErrorResponse> handleResourceAccessException(ResourceAccessException exception) {

		LOGGER.error(RESOURCE_REQUEST_MESSAGE , exception.getCause());
		ErrorResponse errorResponse = setErrorResponse(RESOURCE_REQUEST_MESSAGE ,ErrorCode.SCIO001 , RESOURCE_REQUEST_MESSAGE) ;

		return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MultipleErrorsException.class)
	public ResponseEntity<ErrorResponse> handleMultipleErrorsException(MultipleErrorsException exception) {
		ErrorResponse errorResponse = new ErrorResponse() ;
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setErrors(exception.getErrors());
		LOGGER.error("Multiple Validation Failure");
		return new ResponseEntity<>(errorResponse, BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException exception) {
		ErrorResponse errorResponse = setErrorResponse(ErrorCode.SCIO006.getDescription() ,ErrorCode.SCIO006 , ErrorCode.SCIO006.getDescription()) ;

		LOGGER.error(NO_HANDLER_MESSAGE , exception.getCause());
		return new ResponseEntity<>(errorResponse, BAD_REQUEST);
	}


}