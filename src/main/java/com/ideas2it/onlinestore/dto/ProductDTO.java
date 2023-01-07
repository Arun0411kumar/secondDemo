/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ideas2it.onlinestore.util.constants.Constant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * It is a simple JavaBean domain object representing a product.
 *
 * @author Sangeetha Ilangovan
 * @version 1.0
 * @since 20.12.2022
 */
@Getter
@Setter
@Builder
public class ProductDTO {

	private Long id;
	@Min(1)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer quantity;
	@Min(1)
	private Double price;
	@NotNull(message = Constant.EXPIRY_DATE_COMPULSORY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date dateOfExpire;
	@NotNull(message = Constant.MANUFACTURE_DATE_COMPULSORY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date dateOfManufacture;
	@NotBlank(message = Constant.DESCRIPTION_COMPULSORY)
	private String description;
	@NotBlank(message = Constant.PRODUCT_NAME_COMPULSORY)
	@Pattern(regexp = Constant.REGEX_FOR_NAME, message = Constant.INVALID_NAME)
	private String name;
	@NotNull(message = Constant.BRAND_COMPULSORY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private BrandDTO brand;
	@NotNull(message = Constant.CATEGORY_COMPULSORY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private CategoryDTO category;
	@NotNull(message = Constant.SUB_CATEGORY_COMPULSORY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private CategoryDTO subCategory;
	@JsonIgnore
	private List<WishlistDTO> wishlists;
}