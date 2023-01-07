/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.service;

import java.util.List;
import com.ideas2it.onlinestore.dto.WishlistDTO;
import com.ideas2it.onlinestore.model.Product;
import com.ideas2it.onlinestore.model.User;
import com.ideas2it.onlinestore.model.Wishlist;

/**
 * Interface for WishlistService.
 *
 * @author - Gokul V
 * @version - 1.0
 * @since - 2022-12-17
 */
public interface WishlistService {

    /**
     * <p>Create wishlist to user using given wishlist details.
     * if the given details is not valid throws RuntimeException.</p>
     *
     * @param wishlist        details of the wishlist.
     * @return WishlistDTO    details of the created wishlist.
     */
    WishlistDTO createWishlist(WishlistDTO wishlist, User user) ;

    /**
     * <p>Get the wishlist using given wishlist id.
     * if the given wishlist id is not exists throws RuntimeException
     * otherwise returns wishlist object.</p>
     *
     * @param id               id of the wishlist.
     * @return Wishlist        details of the wishlist.
     */
    Wishlist getWishlistById(long id);

    /**
     * <p>Add product to the user wishlist using the given product id
     * if the product id is not exists throws RuntimeException
     * otherwise returns status message.</p>
     *
     * @param productId     product id of the product.
     * @return String       status message.
     */
    String addProductToWishlist(long productId);

    /**
     * <p>Remove product from the user wishlist using given product id.
     * if the given product id is not exists throws RuntimeException
     * otherwise returns status message.</p>
     *
     * @param productId     product id of the product.
     * @return String       status message.
     */
    String removeProductFromWishlist(long productId);

    /**
     * <p>Get Products from the user wishlist.
     * if the products not exists throws RuntimeException
     * otherwise returns products details.</p>
     *
     * @return List<Product>    details of the products.
     */
    List<Product> getAllWishlistProducts();
}
