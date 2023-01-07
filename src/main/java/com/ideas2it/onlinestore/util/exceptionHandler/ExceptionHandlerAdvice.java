/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.util.exceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ideas2it.onlinestore.model.Message;
import com.ideas2it.onlinestore.util.customException.DataNotFoundException;
import com.ideas2it.onlinestore.util.customException.InsufficientStockException;
import com.ideas2it.onlinestore.util.customException.InvalidInputException;
import com.ideas2it.onlinestore.util.customException.OnlineStoreException;
import com.ideas2it.onlinestore.util.customException.RedundantDataException;
import com.ideas2it.onlinestore.util.customException.ResourcePersistenceException;

/**
 * This class is used to handle the runtime exception
 *
 * @author Arunkumar
 * @version 1.0
 * @since 16-12-2022
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

	private Logger logger = LogManager.getLogger(ExceptionHandlerAdvice.class);

	/**
	 * <p>
	 * This method handle the method argument not valid exception.
	 * Once user give a wrong input (like null, blank etc..) this exception
	 * throw from the controller that controller send this advice class.
	 * This method notify the user who entered wrong input.
	 * Inside that method make one map object which key contain field of user give
	 * field and value as custom message.
	 * </p>
	 *
	 * @param exception                            -
	 * @return ResponseEntity<Map<String, String>> - map object as a response entity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleInvalidArgumentException(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	/**
	 * <p>
	 * This method handle the redundant data exception.
	 * Once user enter some entity it will save the entity into
	 * database at that time if Incase inside that object one field
	 * have already inserted at that time this exception will throw
	 * from the controller that controller send this advice class.
	 * This method notify the user who entered wrong input.
	 * </p>
	 *
	 * @param exception  - this exception represent RedundantDataException
	 * @return message   - this object contain all field in message
	 */
	@ExceptionHandler(RedundantDataException.class)
	public Message handleCategoryExistException(RedundantDataException exception) {
		return getMessage(exception, HttpStatus.CONFLICT);
	}

	/**
	 * <p>
	 * This method handle the resource persistence exception.
	 * Once user enter some entity it will save the entity into
	 * database at that time if Incase it not save the persistence
	 * object it will return null that time this exception.
	 * This method notify the user who entered wrong input.
	 * </p>
	 *
	 * @param exception  - this exception represent ResourcePersistenceException
	 * @return message   - this object contain all field in message
	 */
	@ExceptionHandler(ResourcePersistenceException.class)
	public Message handleResourcePersistenceException(ResourcePersistenceException exception) {
		return getMessage(exception, HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * <p>
	 * This method handle the data not found exception.
	 * This exception is used to handle situations where a user's
	 * expected quantity is more than the available quantity for a
	 * particular product.
	 * </p>
	 *
	 * @param exception - this exception represent dataNotFoundException
	 * @return message  - this object contain all field in message
	 */
	@ExceptionHandler(InsufficientStockException.class)
	public Message handleInsufficientStockException(InsufficientStockException exception) {
		return getMessage(exception, HttpStatus.INSUFFICIENT_STORAGE);
	}

	/**
	 * <p>
	 * This method handle the data not found exception.
	 * Once user give a wrong input (like id should not present
	 * in database etc..) this exception
	 * throw from the controller that controller send this advice class.
	 * This method notify the user who entered wrong input.
	 * </p>
	 *
	 * @param exception - this exception represent dataNotFoundException
	 * @return message  - this object contain all field in message
	 */
	@ExceptionHandler(DataNotFoundException.class)
	public Message handleUserNotFoundException(DataNotFoundException exception) {
		return getMessage(exception, HttpStatus.NOT_FOUND);
	}

	/**
	 * <p>
	 * This method handle the Invalid Input Exception.
	 * Once user enter invalid at that time this exception will throw
	 * from the controller that controller send this advice class.
	 * This method notify the user who entered wrong input.
	 * </p>
	 *
	 * @param exception  - this exception represent RedundantDataException
	 * @return message   - this object contain all field in message
	 */
	@ExceptionHandler(InvalidInputException.class)
	public Message handleOnlineStoreException(InvalidInputException exception) {
		return getMessage(exception, HttpStatus.BAD_REQUEST);
	}

	/**
	 * <p>
	 * This method get the runtime exception and http status and put
	 * into message object and send that object.
	 * This method also log the runtime exception message by using
	 * getMessage() method and also log the error stack trace it's
	 * for developer purpose. This method return message object whenever
	 * this method was called.
	 * </p>
	 *
	 * @param exception  - This exception represent runtime exception
	 * @param httpStatus - this status represent http status
	 * @return message   - details of the message object
	 */
	private Message getMessage(RuntimeException exception, HttpStatus httpStatus) {
		logger.error(exception.getMessage());
		logger.error(exception.getStackTrace());
		return new Message(httpStatus.value(), new Date(), exception.getMessage());
	}
}