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

import com.ideas2it.onlinestore.model.Category;

/**
 * This interface handles category read operation by category name
 *
 * @version 1.0
 * @author arunkumar
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

	/**
	 * <p>
	 * This method used to get the sub categories by using category name
	 * If name is not valid it return null
	 * </p>
	 *
	 * @param name      - this name can be category or sub category
	 * @return category - details of the category object
	 */
	@Query("from Category where name = :name")
	Category getCategoryOrSubCategory(String name);
}