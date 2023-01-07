/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.onlinestore.dto.OrderDTO;
import com.ideas2it.onlinestore.model.Message;
import com.ideas2it.onlinestore.service.OrderService;
import com.ideas2it.onlinestore.util.constants.Constant;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * <p>This is a controller class for Order module.
 * It has only those methods that need to be exposed to the
 * end user. The class interacts and forwards the user requests
 * to its corresponding service and serves the responses to the
 * client accordingly.</p>
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * <p>This methods implements cart check-out logic. It
	 * tracks the contents of cart for the currently logged-in
	 * user and takes an addressId as a parameter that
	 * corresponds to the default delivery address of the user.
	 * The method return an OrderDTO object that contains order details.</p>
	 * <p>The method generates a custom exception (DataNotFoundException)
	 * if the address id does not correspond to a valid resource.</p>
	 *
	 * @param addressId(Id of the default delivery address of the user)
	 * @return OrderDTO(contains details of the placed order)
	 */
	@PostMapping("address/{id}")
	@ApiOperation(value = Constant.PLACE_ORDER,
			notes = Constant.PLACE_ORDER_DESCRIPTION,
			response = OrderDTO.class)
	public OrderDTO placeOrder(
			@ApiParam(value = "Delivery address ID")
			@PathVariable long id) {

		return orderService.placeOrder(id);
	}

	/**
	 * <p>This method is user to retrieve the information for a
	 * particular order using the orderId corresponding to that
	 * order. The method only accepts numeric values</p>
	 * <p>The method throws a custom exception(DataNotFoundException)
	 * if the entered order id does not correspond to a valid order</p>
	 *
	 * @param id(Id of the order whose details are to be fetched)
	 * @return OrderDTO(contains required details of the order)
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = Constant.SHOW_PARTICULAR_ORDER,
			notes = Constant.SHOW_PARTICULAR_ORDER_DESCRIPTION,
			response = OrderDTO.class)
	public OrderDTO getOrderById(
			@ApiParam(value = "Order ID")
			@PathVariable long id) {

		return orderService.getOrderById(id);
	}

	/**
	 * <p>This method is used to retrieve all the orders that have
	 * been placed so far by a particular user.It fetches the entire
	 * order history for the particular user<p>
	 * <p>The method returns an empty list if the user has not placed
	 * any orders at all</p>
	 * @return List<OrderDTO>( returns a list of all the orders
	 * placed by the user)
	 */
	@GetMapping("/all")
	@ApiOperation(value = Constant.SHOW_ALL_ORDERS,
			notes = Constant.SHOW_ALL_ORDERS_DESCRIPTION,
			response = OrderDTO.class)
	public List<OrderDTO> getAllOrders() {

		return orderService.getAllOrders();

	}

	/**
	 * <p>This method takes an orderId as input and calls on the
	 * corresponding service to to cancel the order. A response
	 * is generated which contains the status of order cancellation.</p>
	 * <p>The method throws a custom exception (DataNotFoundException)
	 * if the entered id does not correspond a an active order or if
	 * there is no order corresponding to the id.</p>
	 *
	 * @param orderId(Id of order that the user wishes to cancel)
	 * @return ResponseEntity (contains custom message that displays
	 * the status of order cancellations)
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = Constant.UPDATE_ORDER,
			notes = Constant.UPDATE_ORDER_DESCRIPTION,
			response = Message.class)
	public ResponseEntity<Message> updateOrder(
			@ApiParam(name = "ID", value = "Order ID")
			@RequestParam("ID") long orderId) {

		if (orderService.updateOrder(orderId)) {
			Message message = new Message(
					HttpStatus.ACCEPTED.value(),
					new Date(),
					Constant.ORDER_CANCELLED
			);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
		Message message = new Message(
				HttpStatus.NOT_MODIFIED.value(),
				new Date(),
				Constant.ORDER_CANCELATION_FAILURE
		);
		return new ResponseEntity<Message>(message, HttpStatus.NOT_MODIFIED);
	}

}