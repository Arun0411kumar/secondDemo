/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.onlinestore.model.Cart;

/**
 * This interface provides methods related to operations on Cart.
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
public interface CartRepository extends JpaRepository<Cart, Long> {

}