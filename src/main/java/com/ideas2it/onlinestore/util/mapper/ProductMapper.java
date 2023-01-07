/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.util.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ideas2it.onlinestore.dto.ProductDTO;
import com.ideas2it.onlinestore.model.Product;

/**
 * Converts product.
 *
 * @author Sangeetha Ilangovan
 * @version 1.0
 * @since 21.12.2022
 */
@Component
public class ProductMapper {

	/**
	 * Converts productDTO to product
	 *
	 * @param productDTO
	 * @return product
	 */
	public static Product convertProductDTOToProduct(ProductDTO productDTO) {
		Product product = null;

		if (null != productDTO) {
			product = Product.builder().id(productDTO.getId()).brand(
							BrandMapper.convertBrandDTOToBrand(productDTO.getBrand()))
					.category(CategoryMapper.convertDTOToEntity(productDTO.getCategory())).subCategory(
							CategoryMapper.convertDTOToEntity(productDTO.getSubCategory())).description(
							productDTO.getDescription()).name(productDTO.getName()).price(
							productDTO.getPrice()).dateOfManufacture(
							productDTO.getDateOfManufacture()).dateOfExpire(
							productDTO.getDateOfExpire()).quantity(productDTO.getQuantity()).build();
		}
		return product;
	}

	/**
	 * Converts product to productDTO
	 *
	 * @param product
	 * @return productDTO
	 */
	public static ProductDTO convertProductToProductDTO(Product product) {
		ProductDTO productDTO = null;

		if (null != product) {
			productDTO = ProductDTO.builder().id(product.getId()).brand(
							BrandMapper.convertBrandToBrandDTO(product.getBrand()))
					.category(CategoryMapper.convertEntityToDTO(product.getCategory())).subCategory(
							CategoryMapper.convertEntityToDTO(product.getSubCategory())).description(
							product.getDescription()).name(product.getName()).price(
							product.getPrice()).dateOfManufacture(
							product.getDateOfManufacture()).dateOfExpire(
							product.getDateOfExpire()).quantity(product.getQuantity()).build();
		}
		return productDTO;
	}
}