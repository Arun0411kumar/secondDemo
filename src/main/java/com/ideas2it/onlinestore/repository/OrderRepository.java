/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ideas2it.onlinestore.model.Order;

/**
 * This interface provides methods for operations related to Orders.
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Override
	@Query("from Order where deleted = false")
	List<Order> findAll();
}