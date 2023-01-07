/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ideas2it.onlinestore.util.customException.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ideas2it.onlinestore.dto.BrandDTO;
import com.ideas2it.onlinestore.dto.CategoryDTO;
import com.ideas2it.onlinestore.dto.ProductDTO;
import com.ideas2it.onlinestore.model.Brand;
import com.ideas2it.onlinestore.model.Category;
import com.ideas2it.onlinestore.model.Product;
import com.ideas2it.onlinestore.model.Stock;
import com.ideas2it.onlinestore.repository.BrandRepository;
import com.ideas2it.onlinestore.repository.ProductRepository;
import com.ideas2it.onlinestore.service.CategoryService;
import com.ideas2it.onlinestore.service.ProductService;
import com.ideas2it.onlinestore.service.StockService;
import com.ideas2it.onlinestore.util.constants.Constant;
import com.ideas2it.onlinestore.util.mapper.BrandMapper;
import com.ideas2it.onlinestore.util.mapper.ProductMapper;

/**
 * Provides various methods to get, insert, update and delete product
 *
 * @author Sangeetha Ilangovan
 * @version 1.0
 * @since 12.12.2022
 */
@Service
public class ProductServiceImpl implements ProductService {

	private CategoryService categoryService;
	private BrandRepository brandRepository;
	private ProductRepository productRepository;
	private StockService stockService;
	private Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	@Autowired
	public ProductServiceImpl(CategoryService categoryService, StockService stockService,
							  BrandRepository brandRepository, ProductRepository productRepository) {
		this.categoryService = categoryService;
		this.stockService = stockService;
		this.brandRepository = brandRepository;
		this.productRepository = productRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductDTO addProduct(ProductDTO product) throws OnlineStoreException {
		int quantity = product.getQuantity();
		Date dateOfManufacture = product.getDateOfManufacture();
		Date dateOfExpire = product.getDateOfExpire();

		if (dateOfManufacture.before(dateOfExpire)) {
			Product createdProduct = productRepository.findByNameAndDescription(product.getName(),
					product.getDescription());

			if (null != createdProduct) {
				product = ProductMapper.convertProductToProductDTO(productRepository.save(createdProduct));
			} else {
				product = ProductMapper.convertProductToProductDTO(
						productRepository.save(ProductMapper.convertProductDTOToProduct(product)));
			}
		} else {
			throw new InvalidInputException(Constant.INVALID_MANUFACTURE_EXPIRY_DATE);
		}
		product.setDateOfManufacture(dateOfManufacture);
		product.setDateOfExpire(dateOfExpire);
		product.setQuantity(quantity);
		logger.info(Constant.PRODUCT_ADDED_SUCCESSFULLY + Constant.WITH_PRODUCT_NAME + product.getName());
		return createStockForProduct(product);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductDTO createStockForProduct(ProductDTO product) throws OnlineStoreException {

		if (null != product) {
			Stock stock = stockService.addStock(ProductMapper.convertProductDTOToProduct(product));

			if (null != stock) {
				logger.info(Constant.STOCK_ADDED_SUCCESSFULLY + Constant.WITH_PRODUCT_NAME + product.getName());
				return product;
			} else {
				throw new ResourcePersistenceException(Constant.STOCK_CREATION_FAILED);
			}
		} else {
			throw new ResourcePersistenceException(Constant.PRODUCT_CREATION_FAILED);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductDTO> getAll() throws OnlineStoreException {
		List<ProductDTO> availabeProducts = new ArrayList<ProductDTO>();
		List<Product> products = productRepository.findAll();

		for (Product product : products) {

			if (!product.isDeleted()) {
				availabeProducts.add(ProductMapper.convertProductToProductDTO(product));
			}
		}
		logger.info(Constant.GETTING_PRODUCTS_SUCCESS);
		return availabeProducts;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductDTO updateProduct(ProductDTO product) throws OnlineStoreException {

		if (null != getById(product.getId())) {
			product = ProductMapper.convertProductToProductDTO(
					productRepository.save(ProductMapper.convertProductDTOToProduct(product)));

			if (null == product) {
				throw new ResourcePersistenceException(Constant.PRODUCT_UPDATION_FAILED);
			}
		}
		logger.info(Constant.PRODUCT_UPDATION_SUCCESS + Constant.WITH_PRODUCT_NAME + product.getName());
		return product;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductDTO getById(Long productId) throws OnlineStoreException {
		Product product = productRepository.findById(productId).orElse(null);

		if (null == product || product.isDeleted()) {
			throw new DataNotFoundException(Constant.PRODUCT_NOT_FOUND);
		}
		logger.info(Constant.GETTING_PRODUCT_SUCCESS + Constant.WITH_PRODUCT_NAME + product.getName());
		return ProductMapper.convertProductToProductDTO(product);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductDTO> getByCategory(String categoryName) throws OnlineStoreException {
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		List<Product> availableProducts = new ArrayList<Product>();
		CategoryDTO category = categoryService.getCategoryByName(categoryName);

		if (!getAll().isEmpty()) {
			availableProducts = productRepository.findByCategory(category.getId());

			if (!availableProducts.isEmpty()) {

				for (Product product : availableProducts) {
					products.add(ProductMapper.convertProductToProductDTO(product));
				}
			}
		}
		logger.info(Constant.GETTING_PRODUCTS_SUCCESS);
		return products;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductDTO> getBySubCategory(String subCategoryName) throws OnlineStoreException {
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		List<Product> availableProducts = new ArrayList<Product>();
		CategoryDTO subCategory = categoryService.getSubCategoryByName(subCategoryName);

		if (!getAll().isEmpty()) {
			availableProducts = productRepository.findBySubCategory(subCategory.getId());

			if (!availableProducts.isEmpty()) {

				for (Product product : availableProducts) {
					products.add(ProductMapper.convertProductToProductDTO(product));
				}
			}
		}
		logger.info(Constant.GETTING_PRODUCTS_SUCCESS);
		return products;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductDTO> getByBrand(Long brandId) throws OnlineStoreException {
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		List<Product> availableProducts = new ArrayList<Product>();

		if (!getAllBrands().isEmpty() && !getAll().isEmpty()) {

			if (null != getBrand(brandId)) {
				availableProducts = productRepository.findByBrand(brandId);

				if (!availableProducts.isEmpty()) {

					for (Product product : availableProducts) {
						products.add(ProductMapper.convertProductToProductDTO(product));
					}
				}
			}
		}
		logger.info(Constant.GETTING_PRODUCTS_SUCCESS);
		return products;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BrandDTO addBrand(BrandDTO brand) throws OnlineStoreException {

		if (isBrandUnique(brand)) {
			brand = BrandMapper.convertBrandToBrandDTO(brandRepository.save(BrandMapper.convertBrandDTOToBrand(brand)));

			if (null == brand) {
				throw new ResourcePersistenceException(Constant.BRAND_CREATION_FAILED);
			}
		} else {
			throw new RedundantDataException(Constant.DUPLICATE_BRAND_NAME);
		}
		logger.info(Constant.BRAND_CREATION_SUCCESS + Constant.WITH_BRAND_NAME + brand.getName());
		return brand;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BrandDTO> getAllBrands() throws OnlineStoreException {
		List<BrandDTO> availableBrands = new ArrayList<BrandDTO>();
		List<Brand> brands = brandRepository.findAll();

		if (!brands.isEmpty()) {

			for (Brand brand : brands) {

				if (!brand.isDeleted()) {
					availableBrands.add(BrandMapper.convertBrandToBrandDTO(brand));
				}
			}
		}
		logger.info(Constant.BRANDS_FOUND);
		return availableBrands;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BrandDTO updateBrand(BrandDTO brand) throws OnlineStoreException {

		if (!getAllBrands().isEmpty()) {

			if (null != getBrand(brand.getId())) {

				if (isBrandUnique(brand)) {
					brand = BrandMapper
							.convertBrandToBrandDTO(brandRepository.save(BrandMapper.convertBrandDTOToBrand(brand)));

					if (null == brand) {
						throw new ResourcePersistenceException(Constant.BRAND_UPDATION_FAILED);
					}
				} else {
					throw new RedundantDataException(Constant.DUPLICATE_BRAND_NAME);
				}
			}
		}
		logger.info(Constant.BRAND_UPDATION_SUCCESS + Constant.WITH_BRAND_NAME + brand.getName());
		return brand;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isBrandUnique(BrandDTO brand) {
		return null == brandRepository.findByName(brand.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BrandDTO getBrand(Long brandId) throws OnlineStoreException {
		Brand brand = brandRepository.findById(brandId).orElse(null);

		if (null == brand || brand.isDeleted()) {
			throw new DataNotFoundException(Constant.BRAND_NOT_FOUND);
		}
		logger.info(Constant.BRAND_FOUND + Constant.WITH_BRAND_NAME + brand.getName());
		return BrandMapper.convertBrandToBrandDTO(brand);
	}
}