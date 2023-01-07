/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ideas2it.onlinestore.model.Stock;

/**
 * This interface handles stock read, add quantity, add quantity, and delete 
 * operation as well as read operation by product by name
 *
 * @version 1.0
 * @author arunkumar
 */
public interface StockRepository extends JpaRepository<Stock, Long> {

	/**
	 * <p>
	 * This method is used to fetch stock product if it is valid
	 * its return list of stock object else it empty
	 * </p>
	 *
	 * @param productName - name of the product
	 * @return List<Stock> - list of stock products
	 */
	@Query("from Stock where productName = :productName")
	List<Stock> findByProductName(String productName);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Query("from Stock where deleted = false")
	List<Stock> findAll();
}