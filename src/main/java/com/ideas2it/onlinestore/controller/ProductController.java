/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.onlinestore.dto.BrandDTO;
import com.ideas2it.onlinestore.dto.ProductDTO;
import com.ideas2it.onlinestore.dto.StockDTO;
import com.ideas2it.onlinestore.service.ProductService;
import com.ideas2it.onlinestore.service.StockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Provides interaction between user and application.
 *
 * @author Sangeetha Ilangovan
 * @version 1.0
 * @since 12.12.2022
 */
@RestController
@RequestMapping("${server.servlet.contextPath}/products")
public class ProductController {

	private ProductService productService;
	private StockService stockService;

	@Autowired
	private ProductController(ProductService productService, StockService stockService) {
		this.productService = productService;
		this.stockService = stockService;
	}

	/**
	 * <p>This method is used to insert a product by the user input. It gets
	 * the input from the user as a request.The inputs entered by the user
	 * should be valid and also the category, sub category, brand specified
	 * in the product must already exists in the database otherwise exception
	 * is thrown.If it is valid, the product is sent to its corresponding
	 * service methods to insert the new product.If it is created successfully,
	 * then the created product is returned.</p>
	 *
	 * @param product - the product to inserted
	 * @return productDTO - created product
	 */
	@PostMapping
	@ApiOperation(value = "Adds new product",
			notes = "Seller can Add new products",
			response = ProductDTO.class)
	private ProductDTO addProduct(@Valid @RequestBody ProductDTO product) {
		return productService.addProduct(product);
	}

	/**
	 * <p>This method is used to get all the product.It calls the corresponding
	 * service method to check whether if products exists otherwise
	 * throws no products found exception.If the products exists, it returns
	 * the list of products available.</p>
	 *
	 * @return list of products available
	 */
	@GetMapping("/all")
	@ApiOperation(value = "Shows all the projects",
			notes = "User can view all the products available",
			response = ProductDTO.class)
	private List<ProductDTO> getAllProducts() {
		return productService.getAll();
	}

	/**
	 * <p>This method is used to get all the product by a particular brand.It
	 * calls the corresponding service method to check whether if brand and
	 * products exists otherwise throws no products found or brand not found
	 * exception.If the products exists in that brand, it returns the list of products
	 * available in that brand.</p>
	 *
	 * @param id - id of the brand
	 * @return list of products available in that brand
	 */
	@GetMapping("/brand/{id}")
	@ApiOperation(value = "Shows products by brand",
			notes = "User can view all the products available in a particular brand",
			response = ProductDTO.class)
	private List<ProductDTO> getProductsByBrand(@ApiParam(name = "ID",
			value = "id of the brand") @PathVariable("id") Long brandId) {
		return productService.getByBrand(brandId);
	}

	/**
	 * <p>This method is used to get all the product by a particular category.It
	 * calls the corresponding service method to check whether if category and
	 * products exists otherwise throws no products found or category not found
	 * exception.If the products exists in that category, it returns the list of products
	 * available in that category.</p>
	 *
	 * @param name - category name to get products
	 * @return list of products available in that category name.
	 */
	@GetMapping("/category/{name}")
	@ApiOperation(value = "Shows products by category",
			notes = "User can view all the products available in a particular category",
			response = ProductDTO.class)
	private List<ProductDTO> getProductsByCategory(@ApiParam(name = "Name", value = "name "
			+ "of the category") @PathVariable("name") String categoryName) {
		return productService.getByCategory(categoryName);
	}

	/**
	 * <p>This method is used to get all the product by a particular sub category.It
	 * calls the corresponding service method to check whether if sub category and
	 * products exists otherwise throws no products found or sub category not found
	 * exception.If the products exists in that sub category, it returns the list of products
	 * available in that sub category.</p>
	 *
	 * @param name - sub category name
	 * @return list of products available in that sub category name.
	 */
	@GetMapping("/subcategory/{name}")
	@ApiOperation(value = "Shows products by sub category",
			notes = "User can view all the products available in a particular sub category",
			response = ProductDTO.class)
	private List<ProductDTO> getProductsBySubCategory(@ApiParam(name = "Name",
			value = "Name of the sub category") @PathVariable("name") String subCategoryName) {
		return productService.getBySubCategory(subCategoryName);
	}

	/**
	 * <p>This method is used to update a product by the user input. It gets
	 * the input from the user as a request.The inputs entered by the user
	 * should be valid and also the category, sub category, brand specified
	 * in the product must already exists in the database otherwise exception
	 * is thrown.If it is valid, the product is sent to its corresponding
	 * service methods to update the exist product.If it is updated successfully,
	 * then the updated product is returned.</p>
	 *
	 * @param id - id of the product to be updated
	 * @param product - product to be updated.
	 * @return product that is updated
	 */
	@PutMapping("/{id}")
	@ApiOperation(value = "Updates products by id",
			notes = "Seller can update the product available",
			response = ProductDTO.class)
	private ProductDTO updateProduct(@ApiParam(name = "ID", value = "id of the product"
			+ " to be updated") @PathVariable("id") Long productId,
									 @RequestBody ProductDTO product) {
		product.setId(productId);
		return productService.updateProduct(product);
	}

	/**
	 * <p>This method is used to get the product by a particular product id.It
	 * calls the corresponding service method to check whether if the
	 * products exists otherwise it throws product not found
	 * exception.If the products exists, it returns the product available.</p>
	 *
	 * @param id - id of the product to be viewed.
	 * @return product that is found.
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "Shows product by id",
			notes = "User can view the product available by id",
			response = ProductDTO.class)
	private ProductDTO getProduct(@ApiParam(name = "ID", value = "id of the product"
			+ " to be viewed") @PathVariable("id") Long productId) {
		return productService.getById(productId);
	}

	/**
	 * <p>This method is used to insert a brand by the user input. It gets
	 * the input from the user as a request.The inputs entered by the user
	 * should be valid otherwise exception is thrown.If it is valid, the brand
	 * is sent to its corresponding service methods to insert the new brand.If
	 * it is created successfully,then the created brand is returned.</p>
	 *
	 * @param brand - brand to be inserted.
	 * @return created brand
	 */
	@PostMapping("/brands")
	@ApiOperation(value = "Adds brand",
			notes = "Admin can create a new brand",
			response = BrandDTO.class)
	private BrandDTO addBrand(@Valid @RequestBody BrandDTO brand) {
		return productService.addBrand(brand);
	}

	/**
	 * <p>This method is used to get all the brands availbale.It calls the corresponding
	 * service method to check whether if brands exist otherwise
	 * throws no brands found exception.If the brands exists, it returns
	 * the list of brands available.</p>
	 *
	 * @return list of brands available.
	 */
	@GetMapping("/brands/all")
	@ApiOperation(value = "Shows all the brands",
			notes = "User can view all the brands",
			response = BrandDTO.class)
	private List<BrandDTO> getBrands() {
		return productService.getAllBrands();
	}

	/**
	 * <p>This method is used to update a brand by the user input. It gets
	 * the input from the user as a request.The inputs entered by the user
	 * should be valid otherwise exception is thrown.If it is valid, the brand is sent to its corresponding
	 * service methods to update the existing brand.If it is updated successfully,
	 * then the updated brand is returned.</p>
	 *
	 * @param id - id of the brand to be updated.
	 * @param brand - brand to be updated
	 * @return updated brand.
	 */
	@ApiOperation(value = "Updates the brand",
			notes = "Admin can update the brand",
			response = BrandDTO.class)
	@PutMapping("/brands/{id}")
	private BrandDTO updateBrand(@ApiParam(name = "ID", value = "id of the brand"
			+ " to be updated") @PathVariable("id") Long brandId,
								 @RequestBody BrandDTO brand) {
		brand.setId(brandId);
		return productService.updateBrand(brand);
	}

	/**
	 * <p>This method is used to get the brand by a particular brand id.It
	 * calls the corresponding service method to check whether if the
	 * brand exists otherwise it throws brand not found
	 * exception.If the brand exists, it returns the brand available.</p>
	 *
	 * @param id - id of the brand
	 * @return brand that is found.
	 */
	@GetMapping("/brands/{id}")
	@ApiOperation(value = "Shows the brand by id",
			notes = "User can view the brand available",
			response = BrandDTO.class)
	private BrandDTO getBrand(@ApiParam(name = "ID", value = "id of the brand "
			+ "to be viewed") @PathVariable("id") Long brandId) {
		return productService.getBrand(brandId);
	}

	/**
	 * <p>This method is used to get all the stocks availbale.It calls the corresponding
	 * service method to check whether if stocks exist otherwise
	 * throws no stocks found exception.If the stocks exists, it returns
	 * the list of stocks available.</p>
	 *
	 * @return list of stocks available.
	 */
	@GetMapping("/stocks/all")
	@ApiOperation(value = "Shows all stocks",
			notes = "Seller or admin can view all the stocks available",
			response = StockDTO.class)
	private List<StockDTO> getStocks() {
		return stockService.getStockProducts();
	}

	/**
	 * <p>This method is used to get the stock by a particular stock id.It
	 * calls the corresponding service method to check whether if the
	 * brand exists otherwise it throws stock not found
	 * exception.If the stock exists, it returns the stock available.</p>
	 *
	 * @param id - id of the stock to be viewed
	 * @return stock that is found.
	 */
	@GetMapping("/stocks/{id}")
	@ApiOperation(value = "Shows the stock by id",
			notes = "User can view the stock available by using id",
			response = StockDTO.class)
	private StockDTO getStock(@ApiParam(name = "ID", value = "id of the stock"
			+ " to be viewed") @PathVariable("id") long id) {
		return stockService.getStockProductById(id);
	}

	/**
	 * <p>This method is used to get all the stocks by a particular seller id.It
	 * calls the corresponding service method to check whether if stocks exist
	 * and seller exists otherwise throws no stocks found or seller not found
	 * exception.If the stocks exist in that seller id, it returns the list of
	 * stocks available in that seller.</p>
	 *
	 * @return list of stocks in that seller id.
	 */
	@GetMapping("/stocks/seller")
	@ApiOperation(value = "Shows the stocks by seller id",
			notes = "Seller or admin can view the stock available by seller",
			response = StockDTO.class)
	private List<StockDTO> getAllStockBySeller() {
		return stockService.getStockProductsBySeller();
	}

	/**
	 * <p>This method is used to delete a available stock.The stock id to be
	 * delete is passed to corresponding service method and checks whether the
	 * stock exist otherwise it throws stock not found exception.If the stock
	 * is found, that stock is deleted. If it is deleted successfully, it
	 * returns true otherwise it throws stock deletion failed exception.</p>
	 *
	 * @param id - id of the stock to be deleted
	 * @return true if stock is deleted.
	 */
	@DeleteMapping("/stocks/{id}")
	@ApiOperation(value = "Deletes the stock by id",
			notes = "Seller or admin can delete the stock available by using id",
			response = Boolean.class)
	private boolean deleteStock(@ApiParam(name = "ID", value = "id of the stock"
			+ " to be deleted") @PathVariable("id") long id) {
		return stockService.deleteStock(id);
	}

	/**
	 * <p>This method is used to update a stock by the user input. It gets
	 * the input from the user as a request.The inputs entered by the user
	 * should be valid otherwise exception is thrown.If it is valid, the stock is sent to its corresponding
	 * service methods to update the existing stock.If it is updated successfully,
	 * then the updated stock is returned.</p>
	 *
	 * @param id - stock id whose stock is to updated
	 * @param stockDTO - stock to be updated
	 * @return updated stock.
	 */
	@PutMapping("/stocks/{id}")
	@ApiOperation(value = "Updates the stock by id",
			notes = "Seller or admin can update the stock available by using id",
			response = Boolean.class)
	private boolean updateStock(
			@ApiParam(name = "ID", value = "id of the stock to be updated")
			@PathVariable("id") long id, @RequestBody StockDTO stockDTO) {
		return stockService.updateStock(id, stockDTO);
	}
}