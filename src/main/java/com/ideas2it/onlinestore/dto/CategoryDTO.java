/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ideas2it.onlinestore.model.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * It represents the category DTO.
 *
 * @author arunkumar
 * @version 1.0
 * @since 16-12-2022
 */
@Getter
@Setter
@Builder
public class CategoryDTO {

	private long id;
	private String name;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Category category;
	@JsonProperty(access = Access.READ_ONLY)
	private List<Category> subCategories;
}