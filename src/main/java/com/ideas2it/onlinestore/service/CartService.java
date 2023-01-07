/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service;

import com.ideas2it.onlinestore.dto.CartDTO;
import com.ideas2it.onlinestore.dto.CartProductInputDTO;

/**
 * <p>This interface represents an interface for Cart.
 * The interface provides loose coupling between this
 * service and the corresponding Cart controller and
 * the flexibility of modifying the business logic without
 * the concern of having conflicts with the other
 * interacting layers.</p>
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
public interface CartService {

	/**
	 * <p>This method is used to create a cart. The method
	 * is called upon by the service corresponding to the
	 * user and is executed whenever there is a successful
	 * user registration. The method takes a CartDTO type
	 * object as input which must have a valid user associated
	 * with it</p>
	 *
	 * @param cartDTO(object containing details of user)
	 * @return CartDTO(object containing details of cart allocated
	 * to a user)
	 */
	CartDTO createCart(CartDTO cartDTO);

	/**
	 * <p>This methods takes an InputDTO type object as a parameter.
	 * This InputDTO contains product identifier "Id" and the
	 * quantity of that product that the user wishes to add
	 * to his cart.</p>
	 *
	 * @param cartProductInputDto(productId and
	 * product quantity that is to be added to cart
	 * @return (CartDTO showing products added to the cart)
	 *
	 */
	public CartDTO addProductToCart(CartProductInputDTO cartProductInputDTO);

	/**
	 * <p>This methods takes a product Id as a parameter.
	 * This Id corresponds to the product that is to be
	 * removed from the cart of the currently logged in user.
	 * The Id must be an numeric value and the field cannot
	 * be empty.</p>
	 *
	 * @param productId(Id of the product that is to be
	 * removed from the currently logged in user's cart)
	 * @return CartDTO (object representing the updated
	 * state of the cart after the concerned product has
	 * been removed from the cart)
	 */
	public CartDTO removeProductFromCart(long productId);

	/**
	 * <p>This methods returns the contents of cart for the
	 * currently logged in user along with the total value
	 * for the cart. The method return a CartDTO objects
	 * that contains the details of all the products that
	 * are present in the user's cart along with the price
	 * for each product. The DTO also holds information
	 * regarding the current total value of the cart. If the
	 * cart is empty the DTO object will have a total value
	 * of zero and the will contain an empty list of
	 * cart_products.</p>
	 *
	 * @return CartDTO (object representing the current state
	 * of the cart).
	 */
	public CartDTO findCartDetails();



}