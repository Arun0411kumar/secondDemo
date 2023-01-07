/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.dto;


import java.util.List;

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
public class CartDTO {

	private long id;

	private double cartTotal;

	private List<CartProductDTO> cartProducts;

	@JsonIgnore
	private UserDTO user;

}
