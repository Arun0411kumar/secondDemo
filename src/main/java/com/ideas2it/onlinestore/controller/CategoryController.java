/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.onlinestore.dto.CategoryDTO;
import com.ideas2it.onlinestore.service.CategoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * The Category controller class that implements an application that Simply read
 * operation called from service to controller class
 *
 * @author arunkumar
 * @version 1.0
 * @since 16-12-2022
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * <p>
	 * This method is covered create category. It's get the category DTO object from
	 * front end If this category DTO fields are valid it will pass the object into
	 * service else it throw MethodArgumentNotValidException
	 * </p>
	 *
	 * @param categoryDTO - details of the categoryDTO
	 * @return ResponseEntity<CategoryDTO> - category DTO object as a response
	 *         entity
	 */
	@PostMapping
	@ApiOperation(value = "Add Category", notes = "Admin level access available only", response = CategoryDTO.class)
	private CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
		System.out.println(categoryDTO.getName());
		return categoryService.createCategory(categoryDTO);
	}

	/**
	 * <p>
	 * This method is used to get list of all categories. It's get all filtered list
	 * of category DTO if categories not exists throws OnlineStoreException
	 * otherwise returns user details.
	 * </p>
	 *
	 * @return List<CategoryDTO> - list of categories as a response entity
	 */
	@GetMapping
	@ApiOperation(value = "get all categories", notes = "Any registered user can accesss this api", response = CategoryDTO.class)
	private List<CategoryDTO> getCategories() {
		return categoryService.getCategories();
	}

	/**
	 * <p>
	 * This method is used to get list of all sub categories by using category name
	 * This category name come from the front end If this category is in the
	 * database it will get the all list of sub category else it will throw
	 * OnlineStoreException
	 * </p>
	 *
	 * @param categoryName - name of the category
	 * @return CategoryDTO - list of sub categories as a response entity
	 */
	@GetMapping("/{name}")
	@ApiOperation(value = "get category and that sub categories", notes = "Any registered user can accesss this api", response = CategoryDTO.class)
	private CategoryDTO getCategoryByName(
			@ApiParam(value = "name of the available category") @PathVariable("name") String categoryName) {
		return categoryService.getCategoryByName(categoryName);
	}
}