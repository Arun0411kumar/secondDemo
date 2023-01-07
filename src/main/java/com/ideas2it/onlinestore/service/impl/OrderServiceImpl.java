/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ideas2it.onlinestore.dto.AddressDTO;
import com.ideas2it.onlinestore.dto.OrderDTO;
import com.ideas2it.onlinestore.model.Cart;
import com.ideas2it.onlinestore.model.CartProduct;
import com.ideas2it.onlinestore.model.Order;
import com.ideas2it.onlinestore.model.OrderProduct;
import com.ideas2it.onlinestore.model.User;
import com.ideas2it.onlinestore.repository.OrderRepository;
import com.ideas2it.onlinestore.service.AddressService;
import com.ideas2it.onlinestore.service.CartProductService;
import com.ideas2it.onlinestore.service.OrderService;
import com.ideas2it.onlinestore.service.StockService;
import com.ideas2it.onlinestore.util.configuration.JwtFilter;
import com.ideas2it.onlinestore.util.constants.Constant;
import com.ideas2it.onlinestore.util.constants.OrderStatus;
import com.ideas2it.onlinestore.util.customException.DataNotFoundException;
import com.ideas2it.onlinestore.util.mapper.OrderMapper;
import com.ideas2it.onlinestore.util.mapper.UserMapper;

/**
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
@Service
public class OrderServiceImpl implements OrderService{

	private final StockService stockService;
	private final AddressService addressService;
	private final OrderRepository orderRepository;
	private final CartProductService cartProductService;

	private Logger logger = LogManager.getLogger(OrderServiceImpl.class);

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository,
							StockService stockService, AddressService addressService,
							CartProductService cartProductService) {

		this.orderRepository = orderRepository;
		this.stockService = stockService;
		this.addressService = addressService;
		this.cartProductService = cartProductService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderDTO placeOrder(long addressId) {
		User user = JwtFilter.threadLocal.get();
		AddressDTO addressDTO = this.addressService.getAddressById(addressId);

		if(null != user && null != addressDTO) {
			Cart cart = user.getCart();
			List<CartProduct> cartProducts = cart.getCartProducts();

			if(!cartProducts.isEmpty() ) {
				Order order = checkoutCart(user, cartProducts, addressDTO);
				for (CartProduct cartProduct : cartProducts) {
					cartProduct.setDeleted(true);
					cartProductService.saveCartProduct(cartProduct);
				}
				OrderDTO orderDTO = OrderMapper.OrderDTOBuilder().order(order).build();
				logger.info(Constant.ORDER_SUCCESSFUL);
				return orderDTO;
			} else {
				logger.error(Constant.EMPTY_CART);
				throw new DataNotFoundException(Constant.EMPTY_CART);
			}
		} else {
			logger.error(Constant.ERROR_MESSAGE_ADDRESS_NOT_ADDED);
			throw new DataNotFoundException(Constant.ADDRESS_NOT_FOUND);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderDTO getOrderById(long orderId) {
		Order order = this.orderRepository.findById(orderId)
				.orElseThrow(()-> new DataNotFoundException("Order not found. ID: " +orderId));
		logger.error(Constant.ORDER_FETCHED);
		return OrderMapper.OrderDTOBuilder().order(order).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OrderDTO> getAllOrders() {
		User user = JwtFilter.threadLocal.get();
		List<Order> orders = user.getOrders();
		List<OrderDTO> orderDTOs = orders.stream()
				.map(order -> OrderMapper.OrderDTOBuilder().order(order).build())
				.collect(Collectors.toList());
		logger.info(Constant.ORDERS_FETCHED);
		return orderDTOs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateOrder(long orderId) {
		boolean orderCancellation = false;
		Order order = this.orderRepository.findById(orderId)
				.orElseThrow(()-> new DataNotFoundException(Constant.ORDER_NOT_FOUND +orderId));

		if (null != order) {
			String orderStatus = order.getStatus().toString();

			if (orderStatus == "CANCELLED") {
				logger.info(Constant.ORDER_ALREADY_CANCELLED);
				throw new DataNotFoundException(Constant.ORDER_ALREADY_CANCELLED+orderId);

			} else if (orderStatus == "DELIVERED") {
				logger.info(Constant.ORDER_NOT_FOUND);
				throw new DataNotFoundException(Constant.ORDER_ALREADY_DELIVERED+orderId);

			} else {
				List<OrderProduct> orderProducts = order.getOrderProducts();

				for (OrderProduct orderProduct : orderProducts) {
					orderProduct.setDeleted(true);
				}
				order.setOrderProducts(orderProducts);
				order.setStatus(OrderStatus.CANCELLED);
				this.orderRepository.save(order);
				increaseStocks(orderProducts);
				orderCancellation = true;
				logger.info(Constant.ORDER_UPDATED);
				return orderCancellation;
			}
		} else {
			logger.error(Constant.ORDER_NOT_FOUND);
			throw new DataNotFoundException(Constant.ORDER_NOT_FOUND);
		}
	}

	/**
	 *
	 * @param user
	 * @param cartProducts
	 * @param addressDTO
	 * @return
	 */
	private Order checkoutCart(User user, List<CartProduct> cartProducts, AddressDTO addressDTO) {
		Order order = Order.builder()
				.user(user)
				.address(UserMapper.convertAddressDTOToDAO(addressDTO))
				.amount(updateCartTotal(cartProducts))
				.orderProducts(convertCartProductsToOrderProducts(cartProducts))
				.date(LocalDate.now())
				.status(OrderStatus.SUCCESSFUL)
				.build();
		this.orderRepository.save(order);
		decreaseStocks(convertCartProductsToOrderProducts(cartProducts));
		return order;
	}

	/**
	 * This methods calculates the current cart total by fetching
	 * the price of each product that has been added to the cart.
	 *
	 * @param cartProducts(List of products that have been added
	 * to the cart)
	 * @return double(current value of the cart)
	 */
	private double updateCartTotal(List<CartProduct> cartProducts) {
		double total = 0;
		cartProducts = cartProducts.stream()
				.filter(cartProduct -> (!cartProduct.isDeleted() ))
				.collect(Collectors.toList());
		for (CartProduct cartProduct : cartProducts) {
			total = total + (cartProduct.getProduct().getPrice() * cartProduct.getQuantity());
		}
		logger.info(Constant.CART_TOTAL_UPDATED);
		return total;
	}

	/**
	 *
	 * @param cartProducts
	 * @return
	 */
	public List<OrderProduct> convertCartProductsToOrderProducts(List<CartProduct> cartProducts) {
		List<OrderProduct> orderedProducts = new ArrayList<>();

		for (CartProduct cartProduct : cartProducts) {
			OrderProduct orderedProduct = OrderProduct.builder()
					.quantity(cartProduct.getQuantity())
					.product(cartProduct.getProduct())
					.build();
			orderedProducts.add(orderedProduct);
		}
		return orderedProducts;
	}

	/**
	 *
	 * @param orderedProducts
	 */
	public void decreaseStocks(List<OrderProduct> orderedProducts) {

		for (OrderProduct orderProduct : orderedProducts) {
			String productName = orderProduct.getProduct().getName();
			int quantity = orderProduct.getQuantity();
			this.stockService.decreaseQuantity(productName, quantity);
		}

	}

	/**
	 *
	 * @param orderedProducts
	 */
	public void increaseStocks(List<OrderProduct> orderedProducts) {

		for (OrderProduct orderProduct : orderedProducts) {
			String productName = orderProduct.getProduct().getName();
			int quantity = orderProduct.getQuantity();
			this.stockService.increaseQuantity(productName, quantity);
		}
	}
}