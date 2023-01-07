/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ideas2it.onlinestore.model.Brand;
import com.ideas2it.onlinestore.util.constants.Constant;

/**
 * Insert, update, delete, find operations for brand are performed here.
 *
 * @author Sangeetha Ilangovan
 * @version 1.0
 * @since 12.12.2022
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

	/**
	 * This method is used to get a brand by it's name.
	 *
	 * @param brandName
	 * @return brand
	 */
	@Query(value = Constant.GET_BY_BRAND_NAME)
	public Brand findByName(String brandName);
}