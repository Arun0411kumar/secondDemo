/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service;

import java.util.List;

import com.ideas2it.onlinestore.dto.BrandDTO;
import com.ideas2it.onlinestore.dto.ProductDTO;
import com.ideas2it.onlinestore.util.customException.OnlineStoreException;

/**
 * All the operations like Create, update, delete, view, validation and other
 * operations are performed.
 *
 * @author Sangeetha Ilangovan
 * @version 1.0
 * @since 12.12.2022
 */
public interface ProductService {

	/**
	 * <p>This method is used to insert a product by the user input.It checks
	 * whether the manufacture date is before expiry date otherwise it throws
	 * invalid input exception.Then it checks whether the product already
	 * exists by name and description.If the product already exist, It just
	 * updates the product otherwise it creates a new product.If the product
	 * is not created,it throws product not created exception.If it is created
	 * successfully, then the corresponding stock for that product is created.
	 * If the stock is not created, it throws stock not created exception.
	 * If both stock and product are successfully created, then the created
	 * product is returned.</p>
	 *
	 * @param product - product to be inserted.
	 * @return product if it is created otherwise null.
	 * @throws OnlineStoreException - throws exception if anything went wrong
	 * while creating product.
	 */
	ProductDTO addProduct(ProductDTO product) throws OnlineStoreException;

	/**
	 * <p>This method is used to get all the product.It checks whether if
	 * products exists otherwise it throws no products found exception.
	 * If the products exists, it returns the list of products available.</p>
	 *
	 * @return list of products
	 * @throws OnlineStoreException - throws exception if anything went wrong
	 * while getting all the products.
	 */
	List<ProductDTO> getAll() throws OnlineStoreException;

	/**
	 * <p>This method is used to get the product by a particular product id.It
	 * checks whether if the product exists otherwise it throws product not
	 * found exception.If the product exists, it returns the product available.</p>
	 *
	 * @param productId - id of the product
	 * @return product if it exists otherwise it throws exception.
	 * @throws OnlineStoreException if product is not found.
	 */
	ProductDTO getById(Long productId) throws OnlineStoreException;

	/**
	 * <p>This method is used to get all the product by a particular category.
	 * It checks whether if category and products exist otherwise throws no
	 * products found or category not found exception.If the products exist in
	 * that category, it returns the list of products available in that category.</p>
	 *
	 * @param categoryId - category id
	 * @return list of product.
	 * @throws OnlineStoreException if product is not found.
	 */
	List<ProductDTO> getByCategory(String categoryName) throws OnlineStoreException;

	/**
	 *  <p>This method is used to get all the product by a particular sub category.It checks whether if sub category and
	 * products exist otherwise throws no products found or sub category not found
	 * exception.If the products exist in that sub category, it returns the list of products
	 * available in that sub category.</p>
	 *
	 * @param subCategoryId - sub category id
	 * @return list of product.
	 * @throws OnlineStoreException if product is not found.
	 */
	List<ProductDTO> getBySubCategory(String subCategoryName) throws OnlineStoreException;

	/**
	 * <p>This method is used to get all the product by a particular brand.It
	 * checks whether if brand and products exist otherwise throws no products
	 * found or brand not found exception.If the products exist in that brand, it
	 * returns the list of products available in that brand.</p>
	 *
	 * @param brandId - id of the brand
	 * @return list of product.
	 * @throws OnlineStoreException if product is not found.
	 */
	List<ProductDTO> getByBrand(Long brandId) throws OnlineStoreException ;

	/**
	 * <p>This method is used to create a brand by the user input.
	 * It checks whether the brand name is unique or not.
	 * If it is not unique,it throws duplicate brand exception.If the brand
	 * is unique,it creates the brand.If the brand is not created, it
	 * throws brand not created exception.If it is created successfully,
	 * then the created brand is returned.</p>
	 *
	 * @param brand - brand to be inserted.
	 * @return brand if it is created otherwise null;
	 * * @throws OnlineStoreException if brands are not created.
	 */
	BrandDTO addBrand(BrandDTO brand) throws OnlineStoreException;

	/**
	 * <p>This method is used to get the all the brands available.It
	 * checks whether if the brands exist otherwise it throws brands not
	 * found exception.If the brands exists, it returns the list of brands available.</p>
	 *
	 * @return list of brands
	 * @throws OnlineStoreException if brands are not found or found but not updated.
	 */
	List<BrandDTO> getAllBrands() throws OnlineStoreException;

	/**
	 * <p>This method is used to update a brand by the user input.It checks
	 * whether the brand exists otherwise it throws brand not found exception.
	 * If it is found, then it checks whether the brand name is unique or not.
	 * If it is not unique,it throws duplicate brand exception.If the brand
	 * is unique,it updates the brand.If the brand is not updated, it
	 * throws brand not updated exception.If it is updated successfully,
	 * then the updated brand is returned.</p>.
	 *
	 * @param  - brand to be updated
	 * @return brand based on whether the brand is updated or not.
	 * @throws OnlineStoreException if something went wrong while updating brand.
	 */
	BrandDTO updateBrand(BrandDTO brand) throws OnlineStoreException;

	/**
	 * <p>This method is used to get the brand by a particular brand id.It
	 * checks whether if the brand exists otherwise it throws product not
	 * found exception.If the brand exists, it returns the brand available.</p>
	 *
	 * @param brandId - id of the brand
	 * @return brand if it exists.
	 * @throws OnlineStoreException if brand not found.
	 */
	BrandDTO getBrand(Long brandId) throws OnlineStoreException;

	/**
	 * <p>This method is used to check whether the brand is unique or not.
	 * It returns true or false based on whether the brand name is
	 * unique or not.</p>
	 *
	 * @param brand - brand to checked whether it is unique or not.
	 * @return true or false based on whether the brand exists by name or not
	 */
	boolean isBrandUnique(BrandDTO brand);

	/**
	 * <p>This method is used to update a product by the user input.It checks
	 * whether the product exists otherwise it throws product not found exception.
	 * If it is found, it updates the product. If the product is not updated, it
	 * throws product not updated exception.If it is updated successfully,
	 * then the updated product is returned.</p>
	 *
	 * @param product - product to be updated.4
	 * @return product based on whether the product is updated or not.
	 * @throws OnlineStoreException if the product is not found or it is not
	 * updated.
	 */
	ProductDTO updateProduct(ProductDTO product) throws OnlineStoreException;

	/**
	 * <p>This method is used to create stock for a product.It Checks
	 * whether product is created and creates stock if product is
	 * created otherwise throws product not created exception. If stock is not created
	 * then it throws stock not created exception. 	If stock is created, then it returns
	 * the created stock.</p>
	 *
	 * @param product
	 * @return product which is inserted
	 * @throws OnlineStoreException if product and stock is not created.
	 */
	ProductDTO createStockForProduct(ProductDTO product) throws OnlineStoreException;
}