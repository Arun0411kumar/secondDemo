/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.util.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.onlinestore.dto.CategoryDTO;
import com.ideas2it.onlinestore.model.Category;

/**
 * This class convert entity to DTO, DTO to entity
 *
 * @author arunkumar
 * @version 1.0
 */
public class CategoryMapper {

	/**
	 * <p>
	 * This method is used change the entity object to
	 * DTO object. This entity is comes from category service
	 * and create one empty object for category DTO and
	 * get the value from category object and set the value
	 * into category DTO then It will return category DTO object
	 * whenever this method was called.
	 * </p>
	 *
	 * @param category     - this represent object of the category
	 * @return categoryDTO - this represent object of the category DTO
	 */
	public static CategoryDTO convertEntityToDTO(Category category) {
		List<Category> subCategories = category.getSubCategories();

		if (null != subCategories) {
			for (Category subCategory: category.getSubCategories()) {
				subCategory.setCategory(null);
				subCategory.setSubCategories(null);
			}
		}
		return CategoryDTO.builder()
				.id(category.getId())
				.name(category.getName())
				.category(category.getCategory())
				.subCategories(subCategories)
				.build();
	}

	/**
	 * <p>
	 * This method is used change the DTO object to
	 * entity object. This category DTO is comes from category service
	 * and create one empty object for category and
	 * get the value from category DTO object and set the value
	 * into category then It will return category object
	 * whenever this method was called.
	 * </p>
	 *
	 * @param categoryDTO - this represent object of the category DTO
	 * @return category   - this represent object of the category
	 */
	public static Category convertDTOToEntity(CategoryDTO categoryDTO) {
		return Category.builder()
				.id(categoryDTO.getId())
				.name(categoryDTO.getName())
				.category(categoryDTO.getCategory())
				.subCategories(categoryDTO.getSubCategories())
				.build();
	}

	/**
	 * <p>
	 * This method is used change the list of entity object to
	 * list of DTO object. This list of category is comes from category service
	 * and create one empty list for category DTO and
	 * add the value from list of category object to list of category DTO object
	 * then It will return list of category DTO object
	 * whenever this method was called.
	 * </p>
	 *
	 * @param categories     - this represent list of the category
	 * @return categoriesDTO - this represent list of the category DTO
	 */
	public static List<CategoryDTO> convertEntityToDTO(List<Category> categories) {
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		for (Category category: categories) {
			categoriesDTO.add(convertEntityToDTO(category));
		}
		return categoriesDTO;
	}
}