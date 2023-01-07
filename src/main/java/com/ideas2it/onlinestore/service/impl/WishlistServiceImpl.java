/*
 * Copyright (c) 2022 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.onlinestore.dto.ProductDTO;
import com.ideas2it.onlinestore.dto.WishlistDTO;
import com.ideas2it.onlinestore.model.User;
import com.ideas2it.onlinestore.util.configuration.JwtFilter;
import com.ideas2it.onlinestore.util.customException.DataNotFoundException;
import com.ideas2it.onlinestore.util.customException.RedundantDataException;
import com.ideas2it.onlinestore.util.customException.ResourcePersistenceException;
import com.ideas2it.onlinestore.util.mapper.ProductMapper;
import com.ideas2it.onlinestore.util.mapper.UserMapper;
import com.ideas2it.onlinestore.model.Product;
import com.ideas2it.onlinestore.model.Wishlist;
import com.ideas2it.onlinestore.repository.WishlistRepository;
import com.ideas2it.onlinestore.service.ProductService;
import com.ideas2it.onlinestore.service.WishlistService;
import com.ideas2it.onlinestore.util.constants.Constant;

/**
 * Service Implementation class for Wishlist.
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-17
 */
@Service
public class WishlistServiceImpl implements WishlistService {
    private WishlistRepository wishlistRepository;
    private ProductService productService;
    private Logger logger = LogManager.getLogger(WishlistServiceImpl.class);

    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository, ProductService productService) {
        this.wishlistRepository = wishlistRepository;
        this.productService = productService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WishlistDTO createWishlist(WishlistDTO wishlistDTO, User user) {
        Wishlist wishlist = UserMapper.convertWishlistDTO(wishlistDTO);
        wishlist.setUser(user);
        wishlist = wishlistRepository.save(wishlist);

        if (!(0 < wishlist.getId())) {
            throw new ResourcePersistenceException(Constant.PROFILE_NOT_CREATED);
        }
        wishlistDTO = UserMapper.convertWishlistDAO(wishlist);
        logger.info(Constant.DETAILS_FETCHED_SUCCESSFULLY);
        return wishlistDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wishlist getWishlistById(long id){
        Wishlist wishlist = wishlistRepository.findById(id).orElse(null);

        if (null == wishlist) {
            throw new DataNotFoundException(Constant.ERROR_MESSAGE_WISHLIST_NOT_FOUND);
        }
        logger.info(Constant.DETAILS_FETCHED_SUCCESSFULLY);
        return wishlist;
    }

    /**
     * <p>Update the wishlist using given wishlist details.
     * if the details are not valid throws RuntimeException.</p>
     *
     * @param wishlist    details of the wishlist.
     * @return Wishlist   details of the wishlist.
     */
    private Wishlist updateWishlist(Wishlist wishlist) {
        wishlist = wishlistRepository.save(wishlist);

        if (!(0 < wishlist.getId())) {
            throw new ResourcePersistenceException(Constant.ERROR_MESSAGE_NOT_UPDATED);
        }
        logger.info(Constant.UPDATED_SUCCESSFULLY);
        return wishlist;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addProductToWishlist(long productId) {
        Wishlist wishlist = JwtFilter.getThreadLocal().get().getWishlist();
        ProductDTO productDTO = productService.getById(productId);
        ProductMapper productMapper = new ProductMapper();
        Product product = productMapper.convertProductDTOToProduct(productDTO);

        if (null != wishlist) {
            List<Product> products = wishlist.getProducts();

            if (!(isProductAlreadyExists(products, productId))) {
                products.add(product);
                wishlist.setProducts(products);
                updateWishlist(wishlist);
                logger.info(Constant.PRODUCT_ADDED_SUCCESSFULLY);
                return Constant.PRODUCT_ADDED_SUCCESSFULLY;
            }
        }
        throw new DataNotFoundException(Constant.ERROR_MESSAGE_WISHLIST_NOT_FOUND);
    }

    /**
     * <p>Checks if the given product is already exists
     * in the wishlist or not. if product is already exists
     * throws OnlineStoreException otherwise returns false.</p>
     *
     * @param products       details of products exists in user wishlist.
     * @param productId      id of the product.
     * @return boolean       if product is not exists return false.
     */
    private boolean isProductAlreadyExists(List<Product> products, long productId) {
        List<Long> existingId = products.stream().map(Product::getId).toList();

        for (Long id: existingId) {
            if (id == productId) {
                throw new RedundantDataException(Constant.PRODUCT_ALREADY_EXISTS);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String removeProductFromWishlist(long productId) {
        Wishlist wishlist = JwtFilter.getThreadLocal().get().getWishlist();
        ProductDTO productDTO = productService.getById(productId);

        if (null != wishlist) {
            if (null != productDTO) {
                List<Product> products = wishlist.getProducts();

                if (!products.isEmpty()) {
                    for (Product product: products) {
                        if (product.getId() == productId) {
                            products.remove(product);
                            wishlist.setProducts(products);
                            updateWishlist(wishlist);
                            logger.info(Constant.REMOVED_SUCCESSFULLY);
                            return Constant.REMOVED_SUCCESSFULLY;
                        }
                    }
                }
                throw new DataNotFoundException(Constant.PRODUCTS_NOT_EXISTS);
            }
            throw new DataNotFoundException(Constant.PRODUCT_NOT_FOUND);
        }
        throw new DataNotFoundException(Constant.ERROR_MESSAGE_WISHLIST_NOT_FOUND);
    }

    /**
     * {@inheritDoc}
     */
    public List<Product> getAllWishlistProducts() {
        long id = JwtFilter.getThreadLocal().get().getWishlist().getId();
        Wishlist wishlist = getWishlistById(id);
        return wishlist.getProducts();
    }
}
