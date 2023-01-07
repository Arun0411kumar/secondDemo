/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service;

import java.util.List;

import com.ideas2it.onlinestore.dto.StockDTO;
import com.ideas2it.onlinestore.model.Product;
import com.ideas2it.onlinestore.model.Stock;

/**
 * This interface handles read, add quantity, add quantity, and delete operation
 * for Stock model
 *
 * @version 1.0
 * @author arunkumar
 */
public interface StockService {

	/**
	 * <p>
	 * This method is used to add stock by using product fields.
	 * This method called when the product will be created and this
	 * method called from product service.
	 * It's get the quantity, product name, manufacturing date,
	 * expire date(if expire date exist in the product) and user object
	 * come from jwt util thread local variable. this values set it
	 * into stock object and this will save into database.
	 * If the product name, manufacturing date same it will update
	 * quantity if login user will be the same person.
	 * </p>
	 *
	 * @param product - product object come's from controller
	 * @return Stock - details of the stock
	 */
	Stock addStock(Product product);

	/**
	 * <p>
	 * This method is used to get the Stock products.
	 * It's will call stock repository and retrieve list of stock object
	 * then filter the deleted stock. this method get the
	 * list of stock object and convert into list of stock DTO object
	 * by using stock mapper. if list of stock will be empty it
	 * will throw onlineStoreException.
	 * </p>
	 *
	 * @return List<StockDTO> - list of Stock products
	 */
	public List<StockDTO> getStockProducts();

	/**
	 * <p>
	 * This method is used to get the Stock products by seller
	 * It's will call stock repository and retrieve list of stock object
	 * then filter the deleted stock and based on that seller.
	 * this method get the list of stock object and convert
	 * into list of stock DTO object
	 * by using stock mapper. if list of stock will be empty it
	 * will throw onlineStoreException.
	 * </p>
	 *
	 * @return List<StockDTO> - list of Stock products
	 */
	public List<StockDTO> getStockProductsBySeller();

	/**
	 * <p>
	 * This method is used to get the Stock product by id
	 * This id come from the product controller.
	 * If this id is already in the database it will fetch
	 * the stock object and filter non deleted or not and
	 * it will return stock object
	 * This stock object is converted into stock DTO object
	 * by using stock mapper.
	 * </p>
	 *
	 * @return StockDTO - details of the stock
	 */
	public StockDTO getStockProductById(long id);

	/**
	 * <p>
	 * This method is used to get quantity in stock.
	 * This product name comes from the cartService and
	 * inside this method get the list of stock based on given
	 * product name If the list of stock will be empty it throw
	 * exception else it filter the quantity and add that quantity
	 * one by one then it will return quantities wherever this
	 * method was called.
	 * </p>
	 *
	 * @param productName - name of the product
	 * @return boolean - true/false
	 */
	int getQuantity(String productName);

	/**
	 * <p>
	 * This method is used to decrease quantity in the
	 * stock table once order will placed. This method argument
	 * product name and quantity called from orderService.
	 * If this product stock is there it will fetch
	 * the list of stock and decrease quantity as per the stock
	 * quantity and it return boolean else it will throw
	 * custom exception(OnlineStoreException).
	 * </p>
	 *
	 * @param productName - name of the product
	 * @param quantity - quantity of the product
	 * @return boolean - true/false
	 */
	boolean decreaseQuantity(String productName, int quantity);

	/**
	 * <p>
	 * This method is used to increase quantity
	 * in the stock table once order will cancel.This method argument
	 * product name and quantity called from orderService.
	 * If this product stock is there it will fetch
	 * the list of stock and increase quantity as per the stock
	 * quantity and it return boolean else it will throw
	 * OnlineStoreException if product is not there.
	 * </p>
	 *
	 * @param productName - name of the product
	 * @param quantity - quantity of the product
	 * @return boolean - true/false
	 */
	boolean increaseQuantity(String productName, int quantity);

	/**
	 * <p>
	 * This method is used to delete the stock if one seller want to delete the product.
	 * this method get id from product controller and by using that id get
	 * the stock object from database. This id is not valid id means
	 * it throw exception else it mark as a deleted object then update into database
	 * If it will save database perfectly then only it will return the
	 * true or false wherever this method was called
	 * </p>
	 *
	 * @param id - id of the stock
	 * @return boolean - true/false
	 */
	public boolean deleteStock(long id);

	/**
	 * <p>
	 * This method is used to update the stock object
	 * this method get id from product controller and by using that id get
	 * the stock object from database. This id is not valid id means
	 * it throw exception else it set the all set the id in stock DTO
	 * object then update into database
	 * If it will save database perfectly then only it will return the
	 * true wherever this method was called
	 * </p>
	 *
	 * @param id - id of the stock
	 * @param stockDTO - details of the stock
	 * @return boolean - true/false
	 */
	public boolean updateStock(long id, StockDTO stockDTO);
}