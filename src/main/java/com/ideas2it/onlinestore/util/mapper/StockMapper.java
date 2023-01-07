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

import com.ideas2it.onlinestore.dto.StockDTO;
import com.ideas2it.onlinestore.model.Stock;

/**
 * This class convert entity to DTO, DTO to entity
 *
 * @author arunkumar
 * @version 1.0
 * @since 16-12-2022
 */
public class StockMapper {

	/**
	 * <p>
	 * This method is used change the DTO object to entity object. This DTO is comes
	 * from stock service and create one empty object for stock and get the value
	 * from stock DTO object and set the value into stock then It will return stock
	 * object whenever this method was called.
	 * </p>
	 *
	 * @param stockDTO  - this object represent stock DTO object
	 * @return stock    - this object represent stock object
	 */
	public static Stock convertDTOToEntity(StockDTO stockDTO) {
		return Stock.builder()
				.id(stockDTO.getId())
				.productName(stockDTO.getProductName())
				.quantity(stockDTO.getQuantity())
				.dateOfManufacture(stockDTO.getDateOfManufacture())
				.dateOfExpire(stockDTO.getDateOfExpire())
				.seller(UserMapper.convertUserDTOToDAO(stockDTO.getSeller()))
				.product(ProductMapper.convertProductDTOToProduct(stockDTO.getProduct()))
				.build();
	}

	/**
	 * <p>
	 * This method is used change the entity object to DTO object. This entity is
	 * comes from stock service and create one empty object for stock DTO and get
	 * the value from stock object and set the value into stock DTO then It will
	 * return stock DTO object whenever this method was called.
	 * </p>
	 *
	 * @param stock     - this object represent stock object
	 * @return stockDTO - this object represent stock DTO object
	 */
	public static StockDTO convertEntityToDTO(Stock stock) {
		return StockDTO.builder()
				.id(stock.getId())
				.productName(stock.getProductName())
				.quantity(stock.getQuantity())
				.dateOfManufacture(stock.getDateOfManufacture())
				.dateOfExpire(stock.getDateOfExpire())
				.seller(UserMapper.convertUserDAOToDTO(stock.getSeller()))
				.product(ProductMapper.convertProductToProductDTO(stock.getProduct()))
				.build();
	}

	/**
	 * <p>
	 * This method is used change the list of entity object to list of DTO object.
	 * This list of stock is comes from stock service and create one empty list for
	 * stock DTO and add the value from list of stock object to list of stock DTO
	 * object then It will return list of stock DTO object whenever this method was
	 * called.
	 * </p>
	 *
	 * @param stocks     - list of stock products
	 * @return stockDTOs - list of stockDTO products
	 */
	public static List<StockDTO> convertEntityToDTO(List<Stock> stocks) {
		List<StockDTO> stockDTOs = new ArrayList<>();
		for (Stock stock : stocks) {
			stockDTOs.add(convertEntityToDTO(stock));
		}
		return stockDTOs;
	}
}