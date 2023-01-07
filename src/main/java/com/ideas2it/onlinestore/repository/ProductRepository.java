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
import org.springframework.stereotype.Repository;

import com.ideas2it.onlinestore.model.Product;
import com.ideas2it.onlinestore.util.constants.Constant;

/**
 * Provides various methods to get, insert, update and delete product
 *
 * @author Sangeetha Ilangovan
 * @version 1.0
 * @since 12.12.2022
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * This method is used to get list of products by using it's category.
	 *
	 * @param category
	 * @return list of products
	 */
	@Query(value = Constant.GET_BY_CATEGORY)
	public List<Product> findByCategory(Long categoryId);

	/**
	 * This method is used to get list of products by using it's sub category.
	 *
	 * @param subCategory
	 * @return list of Products
	 */
	@Query(value = Constant.GET_BY_SUB_CATEGORY)
	public List<Product> findBySubCategory(Long subCategoryId);

	/**
	 * This method is used to get list of products by using it's brand if it exists.
	 *
	 * @param brandId
	 * @return list of Products
	 */
	@Query(value = Constant.GET_BY_BRAND)
	public List<Product> findByBrand(Long brandId);

	/**
	 * <p>This method is used to get the product with particular name and description.if the
	 * product with a particular name and description is not exists then it returns null.</p>
	 *
	 * @param name
	 * @param description
	 * @return product
	 */
	@Query(value = Constant.GET_BY_NAME_AND_DESCRIPTION)
	public Product findByNameAndDescription(String name, String description);
}