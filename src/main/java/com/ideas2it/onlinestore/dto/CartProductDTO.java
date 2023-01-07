/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.dto;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
/**
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
@Getter
@Builder
public class CartProductDTO {

	private long id;

	@Min(1)
	private int quantity;
	@JsonIgnore
	private boolean deleted;

	private ProductDTO product;

}
