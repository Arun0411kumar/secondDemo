/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.onlinestore.model.CartProduct;
import com.ideas2it.onlinestore.repository.CartProductRepository;
import com.ideas2it.onlinestore.service.CartProductService;
import com.ideas2it.onlinestore.util.constants.Constant;
/**
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
@Service
public class CartProductServiceImpl implements CartProductService {

	private final CartProductRepository cartProductRepository;

	private Logger logger = LogManager.getLogger(CartProductServiceImpl.class);

	@Autowired
	public CartProductServiceImpl(CartProductRepository cartProductRepository) {
		this.cartProductRepository = cartProductRepository;
	}

	@Override
	public CartProduct saveCartProduct(CartProduct cartProduct) {
		logger.info(Constant.CART_PRODUCT_UPDATED);
		return cartProductRepository.save(cartProduct);
	}
}