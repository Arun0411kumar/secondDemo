/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;

/**
 * This class represents an Input DTO object for
 * adding a product to a user's cart. The class
 * holds two values one representing the product itself
 * and the other representing the quantity for the
 * product.
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
@Getter
@Builder
public class CartProductInputDTO {

    @NotBlank(message = "Please provide a product ID")
    private long productId;

    @NotBlank(message = "Please provide a valid quantity")
    @Min(1)
    private int quantity;


}