/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.onlinestore.dto.StockDTO;
import com.ideas2it.onlinestore.model.Product;
import com.ideas2it.onlinestore.model.Stock;
import com.ideas2it.onlinestore.repository.StockRepository;
import com.ideas2it.onlinestore.service.StockService;
import com.ideas2it.onlinestore.util.configuration.JwtFilter;
import com.ideas2it.onlinestore.util.constants.Constant;
import com.ideas2it.onlinestore.util.customException.DataNotFoundException;
import com.ideas2it.onlinestore.util.customException.ResourcePersistenceException;
import com.ideas2it.onlinestore.util.mapper.StockMapper;


/**
 * This class performs read, add quantity, add quantity, and delete operation
 * This class pass the data into stock repository
 *
 * @author arunkumar
 * @version 1.0
 * @since 16-12-2022
 */
@Service
public class StockServiceImpl implements StockService {

	private StockRepository stockRepository;
	private List<Integer> quantities = new ArrayList<>();
	private Logger logger = LogManager.getLogger(StockServiceImpl.class);

	@Autowired
	public StockServiceImpl(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Stock addStock(Product product) {
		List<Stock> stocks = stockRepository.findByProductName(product.getName());
		Stock stockProduct = null;
		if (stocks.isEmpty()) {
			stockProduct = createStock(product);
		} else {
			for (Stock stock : stocks) {
				if ((stock.getSeller().getId() == JwtFilter.getThreadLocal().get().getId())
						&& (new java.sql.Date(product.getDateOfManufacture().getTime()).toString().substring(0, 10)
						.equals(stock.getDateOfManufacture().toString().substring(0, 10)))
						&& (new java.sql.Date(product.getDateOfExpire().getTime()).toString().substring(0, 10)
						.equals(stock.getDateOfExpire().toString().substring(0, 10)))) {
					stock.setQuantity(stock.getQuantity() + product.getQuantity());
					stockProduct = stockRepository.save(stock);
					break;
				}
			}
			if (null == stockProduct) {
				stockProduct = createStock(product);
			}
		}
		return stockProduct;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StockDTO> getStockProducts() {
		List<Stock> stockProducts = stockRepository.findAll();

		if (null == stockProducts || stockProducts.isEmpty()) {
			logger.error(Constant.ERROR_MESSAGE_EMPTY_LIST);
		}
		return StockMapper.convertEntityToDTO(stockProducts);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized boolean decreaseQuantity(String productName, int quantity) {
		List<Stock> stockProducts = stockRepository.findByProductName(productName);
		boolean isUpdated = false;

		if (!stockProducts.isEmpty()) {
			for (Stock stock : stockProducts) {
				quantities.add(stock.getQuantity());
				quantity = stock.getQuantity() - quantity;
				if (0 > quantity) {
					stock.setQuantity(0);
					stockRepository.save(stock);
					quantity = -(quantity);
				} else {
					stock.setQuantity(quantity);
					stockRepository.save(stock);
					break;
				}
			}
			isUpdated = true;
		} else {
			logger.error(Constant.ERROR_MESSAGE_EMPTY_LIST);
		}
		return isUpdated;
	}

	/**
	 * {@inheritDoc}
	 */
	public Stock getStockProduct(long id) {
		Stock stock = stockRepository.findById(id).orElse(null);

		if (null == stock || stock.isDeleted()) {
			throw new DataNotFoundException(Constant.STOCK_NOT_FOUND);
		}
		return stock;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StockDTO getStockProductById(long id) {
		return StockMapper.convertEntityToDTO(getStockProduct(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getQuantity(String productName) {
		int quantity = 0;
		List<Stock> stockProducts = stockRepository.findByProductName(productName);

		if (stockProducts.isEmpty()) {
			logger.error(Constant.STOCK_NOT_FOUND);
		}
		for (Stock stock : stockProducts) {
			quantity = quantity + stock.getQuantity();
		}
		return quantity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteStock(long id) {
		Stock stock = getStockProduct(id);
		stock.setDeleted(true);
		return !(stockRepository.save(stock).getUpdatedAt().getTime() == stock.getUpdatedAt().getTime());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateStock(long id, StockDTO stockDTO) {
		Stock stock = getStockProduct(id);
		stockDTO.setId(id);
		stock = StockMapper.convertDTOToEntity(stockDTO);
		return !(stockRepository.save(stock).getUpdatedAt().getTime() == stock.getUpdatedAt().getTime());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StockDTO> getStockProductsBySeller() {
		List<StockDTO> stockProducts = new CopyOnWriteArrayList<>();
		stockProducts.addAll(getStockProducts());

		for (StockDTO stock : stockProducts) {
			if (!(stock.getSeller().getId() == JwtFilter.getThreadLocal().get().getId())) {
				stockProducts.remove(stock);
			}
		}
		if (stockProducts.isEmpty()) {
			logger.error(Constant.ERROR_MESSAGE_EMPTY_LIST);
		}
		return stockProducts;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized boolean increaseQuantity(String productName, int quantity) {
		int count = 0;
		boolean isUpdated = false;
		List<Stock> stockProducts = stockRepository.findByProductName(productName);

		if (!stockProducts.isEmpty()) {
			for (Stock stock : stockProducts) {
				quantity = quantities.get(count) - quantity;
				if (0 > quantity) {
					stock.setQuantity(quantities.get(count));
					stockRepository.save(stock);
					quantity = -(quantity);
					count++;
				} else {
					stock.setQuantity(quantities.get(count));
					stockRepository.save(stock);
					break;
				}
			}
			isUpdated = true;
		} else {
			logger.error(Constant.STOCK_NOT_FOUND);
		}
		return isUpdated;
	}

	/**
	 * <p>
	 * This method is used for save the stock into database
	 * It's for reducing boiler plate code.
	 * It's set the all stock fields by using product
	 * </p>
	 *
	 * @param product     - details of the product
	 * @return stock      - details of the stock
	 */
	private Stock createStock(Product product) {
		Stock stock = null;
		stock = Stock.builder()
				.productName(product.getName())
				.quantity(product.getQuantity())
				.dateOfManufacture(product.getDateOfManufacture())
				.dateOfExpire(product.getDateOfExpire())
				.seller(JwtFilter.getThreadLocal().get())
				.product(product)
				.build();
		stock = stockRepository.save(stock);
		if (null == stock) {
			throw new ResourcePersistenceException(Constant.STOCK_CREATION_FAILED);
		}
		return stock;
	}
}