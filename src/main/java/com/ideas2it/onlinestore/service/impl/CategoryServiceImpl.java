/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.onlinestore.dto.CategoryDTO;
import com.ideas2it.onlinestore.model.Category;
import com.ideas2it.onlinestore.repository.CategoryRepository;
import com.ideas2it.onlinestore.service.CategoryService;
import com.ideas2it.onlinestore.util.constants.Constant;
import com.ideas2it.onlinestore.util.customException.DataNotFoundException;
import com.ideas2it.onlinestore.util.customException.RedundantDataException;
import com.ideas2it.onlinestore.util.customException.ResourcePersistenceException;
import com.ideas2it.onlinestore.util.mapper.CategoryMapper;

/**
 * This class performs read all categories, read sub categories by category name
 * This class pass the data into category repository
 *
 * @author arunkumar
 * @version 1.0
 * @since 16-12-2022
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = CategoryMapper.convertDTOToEntity(categoryDTO);
		String name = category.getName();
		Category existingCaytegory = categoryRepository.getCategoryOrSubCategory(name);
		if (null == existingCaytegory) {
			if (null != category.getCategory()) {
				category.setCategory(category.getCategory());
			}
			category = categoryRepository.save(category);
			if (null == category) {
				throw new ResourcePersistenceException(Constant.CATEGORY_NOT_CREATED);
			}
			categoryDTO = CategoryMapper.convertEntityToDTO(category);
		} else {
			throw new RedundantDataException(Constant.CATEGORY_ALREDY_EXIST);
		}
		return categoryDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoryDTO> getCategories() {
		List<Category> categories = new CopyOnWriteArrayList<>();
		categories.addAll(categoryRepository.findAll());

		for (Category category: categories) {
			if (null != category.getCategory()) {
				categories.remove(category);
			}
		}
		if (categories.isEmpty()) {
			logger.info(Constant.ERROR_MESSAGE_EMPTY_LIST);
		}
		return CategoryMapper.convertEntityToDTO(categories);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryDTO getCategoryByName(String categoryName) {
		Category category = categoryRepository.getCategoryOrSubCategory(categoryName);

		if (null == category || null != category.getCategory()) {
			throw new DataNotFoundException(Constant.CATEGORY_NOT_EXISTS);
		}
		return CategoryMapper.convertEntityToDTO(category);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryDTO getSubCategoryByName(String subCategoryName) {
		Category subCategory = categoryRepository.getCategoryOrSubCategory(subCategoryName);

		if (null == subCategory || null == subCategory.getCategory()) {
			throw new DataNotFoundException(Constant.SUB_CATEGORY_NOT_EXISTS);
		}
		return CategoryMapper.convertEntityToDTO(subCategory);
	}
}