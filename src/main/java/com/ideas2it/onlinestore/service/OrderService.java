/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service;

import java.util.List;

import com.ideas2it.onlinestore.dto.OrderDTO;

/**
 * This interface represents an interface for Order.
 * The interface helps to provides loose coupling between this 
 * service and the corresponding Order controller and 
 * the flexibility of modifying the business logic without
 * the concern of having conflicts with the other 
 * interacting layers.
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
public interface OrderService {

	/**
	 * <p>This method takes an addressId as input, fetches and 
	 * validates the address id and implements the
	 * cart checkout functionality by taking the current cart
	 * products and adding them to order.</p>
	 *
	 * @param addressId(Id of default address of the user)
	 * @return OrderDTO(DTO object containing order details)
	 */
	public OrderDTO placeOrder(long addressId);

	/**
	 * <p>This method takes an orderId as input and fetches the details
	 * of the order corresponding to the given order id.</p>
	 *
	 * @param orderId(ID of a particular order)
	 * @return OrderDTO(DTO object with details of the order)
	 */
	public OrderDTO getOrderById(long orderId);

	/**
	 * <p>This method retrieves all orders placed by the currently logged in
	 * user.</p>
	 * @return List(returns a list containing all the orders
	 * that have been places by that user)
	 */
	public List<OrderDTO> getAllOrders();

	/**
	 * <p>This method takes an order Id as input and attempts to cancel the 
	 * order if it is eligible for cancellation. The method first verifies
	 * current status of the order and accordingly cancels it if possible</p>
	 * <p>The method throws a custom exception(DataNotFoundException) if the
	 * id doesn't correspond to an existing active order.</p>
	 *
	 * @param orderId(Id of the order that is to be cancelled)
	 * @return true if order is cancelled else returns false.
	 */
	public boolean updateOrder(long orderId);


}