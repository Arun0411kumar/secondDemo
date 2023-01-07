package com.ideas2it.onlinestore.util.customException;

/**
 * This class is used to convert all message to custom exception
 *
 * @version 1.0
 * @author arunkumar
 */
public class InsufficientStockException extends RuntimeException{

	public InsufficientStockException(String message) {
		super(message);
	}
}