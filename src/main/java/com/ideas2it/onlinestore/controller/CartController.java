/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.onlinestore.dto.CartDTO;
import com.ideas2it.onlinestore.dto.CartProductInputDTO;
import com.ideas2it.onlinestore.service.CartService;
import com.ideas2it.onlinestore.util.constants.Constant;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * <p>This is a controller class for cart module.
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
@RequestMapping("/cart")
public class CartController {

	private final CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	/**
	 * <p>This method adds a product to the cart of currently
	 * logged in user. It takes an input type DTO as a parameter
	 * that contains the information about the product id and desired
	 * quantity that the customer wishes to add to his cart.
	 * The method then passes the information to the corresponding
	 * service where the product is added to the cart and returns
	 * a cartDTO type object showing updated state of the cart
	 * if the product is successfully added to the cart</p>
	 * <p>The method generates a custom exception
	 * (DataNotFoundException) if the intended product is not
	 * available. In case the product it available but the
	 * requested quantity is more than the current available
	 * stock, the method generates another custom exception
	 * (InsufficientStockException).
	 *
	 * @param cartProductInputDTO
	 * @return
	 */
	@PostMapping("/product")
	@ApiOperation(value = Constant.ADD_PRODUCT,
			notes = Constant.ADD_PRODUCT_DESCRIPTION,
			response = CartDTO.class)
	public CartDTO addProductToCart(@RequestBody CartProductInputDTO cartProductInputDTO) {
		return cartService.addProductToCart(cartProductInputDTO);
	}

	/**
	 * <p>This method removes a product from the cart of currently
	 * logged in user. It takes the product id corresponding
	 * to the product that is to be removed from the cart and
	 * passes the information to the corresponding service
	 * where the product is removed from the cart. The method
	 * returns a cart DTO type object showing updated status of
	 * the cart. The method only takes numeric values</p>
	 * <p>The method generates a custom exception
	 * (DataNotFoundException) if the product corresponding to
	 * the entered Id is not available in the cart.</p>
	 *
	 * @param productId (Unique identifier for the product)
	 * @return CartDTO
	 */
	@DeleteMapping("/product/{id}")
	@ApiOperation(value = Constant.REMOVE_PRODUCT,
			notes = Constant.REMOVE_PRODUCT_DESCRIPTION,
			response = CartDTO.class)
	public CartDTO removeProductFromCart(
			@ApiParam(value = "ID of the Product")
			@PathVariable long id) {

		return cartService.removeProductFromCart(id);
	}

	/**
	 * <p>This method displays the current status of the cart.
	 * It shows the list of products that are in the cart for
	 * the currently logged-in user and the total value of the
	 * cart at that point of time.</p>
	 * <p>The methods return an empty list of cart products if
	 * the cart for the currently logged in user is empty.</p>
	 *
	 * @return CartDTO
	 */
	@GetMapping("/products")
	@ApiOperation(value = Constant.CART,
			notes = Constant.CART_DESCRIPTION,
			response = CartDTO.class)
	public CartDTO getUserCartDetails() {
		return cartService.findCartDetails();
	}

}