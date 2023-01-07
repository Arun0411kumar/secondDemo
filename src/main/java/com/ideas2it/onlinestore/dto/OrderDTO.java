/*
 * Copyright (c) 2022 - 2024 Ideas2it, Inc.All rights are reserved.
 *
 * This document is protected by copyright. No part of this document may be
 * reproduced in any form by any means without prior written authorization of
 * Ideas2it and its licensors, if any.
 */
package com.ideas2it.onlinestore.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ideas2it.onlinestore.util.constants.OrderStatus;

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
public class OrderDTO {

	private long id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@JsonProperty(access = Access.READ_ONLY)
	private double amount;

	@JsonProperty(access = Access.READ_ONLY)
	private OrderStatus status;

	@JsonIgnore
	private UserDTO user;

	private List<OrderProductDTO> orderProducts;

	private AddressDTO address;


}
